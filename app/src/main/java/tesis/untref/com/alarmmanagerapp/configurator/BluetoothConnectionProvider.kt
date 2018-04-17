package tesis.untref.com.alarmmanagerapp.configurator

import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.BluetoothConnection

class BluetoothConnectionProvider {

    companion object {
        var bluetoothConnection: BluetoothConnection? = null
    }
}