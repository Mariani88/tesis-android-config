package tesis.untref.com.alarmmanagerapp.configurator.presenter

import android.bluetooth.BluetoothAdapter
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.connection.BluetoothConnectionCreationService
import tesis.untref.com.alarmmanagerapp.configurator.view.MainView

class MainPresenter(private val mainView: MainView, private val bluetoothConnectionCreationService: BluetoothConnectionCreationService) {

    fun checkBluetoothConnection(bluetoothAdapter: BluetoothAdapter?) {
        bluetoothAdapter?.let { checkBluetoothState(it) }
                ?: mainView.reportIncompatibilityBluetooth()
    }

    fun checkUserActivatedBluetooth(userActivatedBluetooth: Boolean) {
        if (userActivatedBluetooth) connectToBluetooth() else mainView.reportBluetoothOnIsRequired()
    }

    fun unregisterReceiver() {
        bluetoothConnectionCreationService.unregisterReceiver()
    }

    private fun checkBluetoothState(bluetoothAdapter: BluetoothAdapter) {
        if (bluetoothAdapter.isEnabled) {
            connectToBluetooth()

        } else {
            mainView.reportBluetoothIsOff()
        }
    }

    private fun connectToBluetooth() {
        bluetoothConnectionCreationService.createConnection({ mainView.goNextView() }, { mainView.reportConnectionError() }, {unregisterReceiver()})
    }
}