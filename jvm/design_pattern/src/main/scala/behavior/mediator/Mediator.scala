package behavior.mediator

trait Mediator:
  def notify(sender: Component, event: String): Unit

abstract class Component(protected val mediator: Mediator):
  def click(): Unit    = mediator.notify(this, "click")
  def keypress(): Unit = mediator.notify(this, "keypress")

class Button(mediator: Mediator) extends Component(mediator)

class Textbox(mediator: Mediator) extends Component(mediator)

class Checkbox(dialog: Mediator) extends Component(dialog):
  private var _checked: Boolean = false
  def check(): Unit = mediator.notify(this, "check")
  def checked: Boolean = _checked
  def checked_=(value: Boolean): Unit = _checked = value

class AuthenticationDialog extends Mediator:
  private var title:Option[String] = None
  private val loginOrRegisterChkBx: Checkbox = new Checkbox(this)
  private val loginUsername: Textbox = new Textbox(this)
  private val loginPassword: Textbox = new Textbox(this)
  private val registrationUsername: Textbox = new Textbox(this)
  private val okBtn = new Button(this)
  private val cancelBtn = new Button(this)

  override def notify(sender: Component, event: String): Unit =
    if sender == loginOrRegisterChkBx && event == "check" then
      if loginOrRegisterChkBx.checked then title = "Log in" else title = "Register"
    if sender == okBtn && event == "click" then
      if loginOrRegisterChkBx.checked then println("Try to find user in the database")
      else println("Try to register user in the system")

