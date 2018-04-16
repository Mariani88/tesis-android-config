package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth

import android.bluetooth.BluetoothDevice
import android.util.Log
import tesis.untref.com.alarmmanagerapp.utils.TAG
import java.util.*

class BluetoothConnectionService(private val bluetoothDevice: BluetoothDevice, private val uuid: String) {

    private var bluetoothConnectionThread: BluetoothConnectionThread? = null

    init {
        connectToDevice()
    }

    fun write(bytes: ByteArray) {
        bluetoothConnectionThread!!.write(bytes)
    }

    fun isConnected() = this.bluetoothConnectionThread!!.isConnected()

    fun retryConnection() {
        connectToDevice()
    }

    fun closeConnection() {
        bluetoothConnectionThread!!.closeConnection()
    }

    private fun connectToDevice() {
        Log.d(TAG, "trying connecting")
        var temporalBluetoothConnectionThread: BluetoothConnectionThread?
        val socket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
        socket.connect()
        Log.d(TAG, "device connected")
        temporalBluetoothConnectionThread = BluetoothConnectionThread(socket)
        temporalBluetoothConnectionThread.start()
        bluetoothConnectionThread = temporalBluetoothConnectionThread
    }
}