package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth

import android.bluetooth.BluetoothDevice
import android.util.Log
import java.io.IOException
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
        var temporalBluetoothConnectionThread: BluetoothConnectionThread? = null

        try {
            val socket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
            socket.connect()
            temporalBluetoothConnectionThread = BluetoothConnectionThread(socket)
            temporalBluetoothConnectionThread.start()
        } catch (e: IOException) {
            Log.d(TAG, "error when connecting to remote device", e)
        }

        bluetoothConnectionThread = temporalBluetoothConnectionThread
    }

    companion object {
        private const val TAG = "MY_APP_DEBUG_TAG"
    }
}