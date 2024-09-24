package creation.factorymethod

interface Button {
    fun render()
    fun onClick(closeDialog: () -> Unit)

}

abstract class Dialog {
    abstract fun createButton(): Button

    fun render() {
        val okButton = createButton()
        okButton.onClick(::closeDialog)
        okButton.render()
    }

    fun closeDialog() {
        println("Dialog is closed")
    }
}

class WindowsButton : Button {
    override fun render() {
        println("Render Windows button")
    }

    override fun onClick(closeDialog: () -> Unit) {
        println("Windows button is clicked")
        closeDialog()
    }

}

class WindowsDialog : Dialog() {
    override fun createButton(): Button {
        return WindowsButton()
    }
}

class HTMLButton : Button {
    override fun render() {
        println("Render HTML button")
    }

    override fun onClick(closeDialog: () -> Unit) {
        println("HTML button is clicked")
        closeDialog()
    }

}

class WebDialog : Dialog() {
    override fun createButton(): Button {
        return HTMLButton()
    }
}

class Application {
    lateinit var dialog: Dialog

    fun initialize() {
        val config = "Windows"
        dialog = when (config) {
            "Windows" -> WindowsDialog()
            "Web" -> WebDialog()
            else -> throw IllegalArgumentException("Unknown config")
        }
    }
}

fun main() {
    val app = Application()
    app.initialize()
    app.dialog.render()
}
