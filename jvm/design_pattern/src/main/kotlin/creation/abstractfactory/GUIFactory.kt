package creation.abstractfactory


interface Button {}
interface Checkbox {}
interface GUIFactory {
    fun createButton(): Button
    fun createCheckbox(): Checkbox

}
