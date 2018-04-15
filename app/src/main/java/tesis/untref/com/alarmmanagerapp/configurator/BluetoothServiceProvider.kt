package tesis.untref.com.alarmmanagerapp.configurator

import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.BluetoothConnectionService

class BluetoothServiceProvider {

    companion object {
        var bluetoothConnectionService: BluetoothConnectionService? = null
    }
}