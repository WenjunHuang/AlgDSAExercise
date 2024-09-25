package behavior.command

abstract class Command(protected val app: Application, protected val editor: Editor):
  protected var backup: Option[String] = None

  final def saveBackup(): Unit =
    backup = editor.text

  final def undo(): Unit =
    backup.foreach(editor.text = _)

  def execute(): Boolean

final class CopyCommand(app: Application, editor: Editor) extends Command(app, editor):
  override def execute(): Boolean =
    app.clipboard = editor.selection
    false

final class CutCommand(app: Application, editor: Editor) extends Command(app, editor):
  override def execute(): Boolean =
    saveBackup()
    app.clipboard = editor.selection
    editor.deleteSelection()
    true

final class PasteCommand(app: Application, editor: Editor) extends Command(app, editor):
  override def execute(): Boolean =
    saveBackup()
    app.clipboard.foreach(editor.replaceSelection)
    true

final class UndoCommand(app: Application, editor: Editor) extends Command(app, editor):
  override def execute(): Boolean =
    app.undo()
    false

final class CommandHistory:
  private var history: List[Command] = Nil

  def push(c: Command) = history = c :: history
  def pop(): Option[Command] = history match
    case Nil => None
    case x :: xs =>
      history = xs
      Some(x)

class Editor:
  private var _text: Option[String]     = None
  private var _selection: Option[Range] = None
  def text: Option[String]              = _text
  def text_=(text: String): Unit        = _text = Some(text)
  def selection: Option[String]         = _selection.zip(_text).map { case (range, text) => text.substring(range.start, range.end) }
  def deleteSelection(): Unit           = _selection = None
  def replaceSelection(text: String): Unit =
    _selection.zip(_text).foreach { case (range, oldText) =>
      _text = Some(oldText.substring(0, range.start) + text + oldText.substring(range.end))
    }

class Button(val label: String):
  var _command: Option[() => Unit] = None

  def command: Option[() => Unit]                  = _command
  def command_=(command: Option[() => Unit]): Unit = _command = command
  def click(): Unit                                = _command.foreach(_.apply())

class Shortcuts:
  private var command: Map[String, () => Unit]            = Map.empty
  def onKeyPress(keys: String, command: () => Unit): Unit = this.command += keys -> command

class Application {
  var clipboard: Option[String]    = None
  var editors: List[Editor]        = Nil
  var activeEditor: Option[Editor] = None
  val history: CommandHistory      = CommandHistory()

  def undo(): Unit =
    history.pop() match
      case Some(cmd) => cmd.undo()
      case None      =>

  def executeCommand(cmd: Command): Unit =
    if cmd.execute() then history.push(cmd)

  def createUI(): Unit =
    val copyButton  = Button("Copy")
    val cutButton   = Button("Cut")
    val pasteButton = Button("Paste")
    val undoButton  = Button("Undo")
    val shortcuts   = Shortcuts()

    val copy = () =>
      activeEditor match
        case Some(editor) =>
          executeCommand(CopyCommand(this, editor))
        case None =>
    copyButton.command = Some(copy)
    shortcuts.onKeyPress("Ctrl+C", copy)

    val cut = () =>
      activeEditor match
        case Some(editor) =>
          executeCommand(CutCommand(this, editor))
    cutButton.command = Some(cut)
    shortcuts.onKeyPress("Ctrl+X", cut)

    val paste = () =>
      activeEditor match
        case Some(editor) =>
          executeCommand(PasteCommand(this, editor))
        case None =>
    pasteButton.command = Some(paste)
    shortcuts.onKeyPress("Ctrl+V", paste)

    val undo = () =>
      activeEditor match
        case Some(editor) =>
          executeCommand(UndoCommand(this, editor))
        case None =>
    undoButton.command = Some(undo)
    shortcuts.onKeyPress("Ctrl+Z", undo)

}

@main
def runCommand: Unit =
  val app = Application()
  app.createUI()
