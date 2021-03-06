package tesis.untref.com.alarmmanagerapp.configurator.view

interface MainView {
    fun reportIncompatibilityBluetooth()
    fun reportBluetoothIsOff()
    fun goNextView()
    fun reportBluetoothOnIsRequired()
    fun reportConnectionError()
    fun hideConnectionButton()
    fun reportSearching()
}