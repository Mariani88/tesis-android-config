package tesis.untref.com.alarmmanagerapp.configurator.presenter

import android.bluetooth.BluetoothAdapter
import tesis.untref.com.alarmmanagerapp.configurator.view.MainView

class MainPresenter(private val mainView: MainView) {

    fun checkBluetoothConnection(bluetoothAdapter: BluetoothAdapter?) {
        bluetoothAdapter?.let { checkBluetoothState(it) }
                ?: mainView.reportIncompatibilityBluetooth()
    }

    fun checkUserActivatedBluetooth(userActivatedBluetooth: Boolean) {
        if (userActivatedBluetooth) mainView.goNextView() else mainView.reportBluetoothOnIsRequired()
    }

    private fun checkBluetoothState(bluetoothAdapter: BluetoothAdapter) {
        if (bluetoothAdapter.isEnabled) {
            mainView.goNextView()
        } else {
            mainView.reportBluetoothIsOff()
        }
    }
}