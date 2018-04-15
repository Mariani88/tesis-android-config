package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth

import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException

class BluetoothConnectionThread(private val bluetoothSocket: BluetoothSocket): Thread()  {
    private val inputStream = bluetoothSocket.inputStream
    private val outputStream = bluetoothSocket.outputStream

    fun write(bytes: ByteArray) {
        try {
            outputStream!!.write(bytes)
        } catch (e: IOException) {
            Log.e(TAG, "Error occurred when sending data", e)
        }
    }

    fun closeConnection() {
        try {
            inputStream.close()
            outputStream.close()
            bluetoothSocket.close()
        } catch (e: IOException) {
            Log.e(TAG, "Could not close the connect socket", e)
        }
    }

    fun isConnected() = bluetoothSocket.isConnected

    companion object {
        private const val TAG = "BT_CONNECTION_THREAD"
    }
}