package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.io.IOException
import java.util.*

class DetectBluetoothBroadcastReceiver(private val bluetoothAdapter: BluetoothAdapter): BroadcastReceiver() {

    // Create a BroadcastReceiver for ACTION_FOUND

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        // When device discovery finds a
        if (BluetoothDevice.ACTION_FOUND == action) {
            // Get the object from the Intent BluetoothDevice

            //todo cada vez que encuentra un device, lo guarda en esta variable, tener en cuenta si hay varios
            val bluetoothDevice = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)

            //todo meter chequeo aca para que haga lo de abajo si encuentra el dispositivo buscado

            val socket = bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
            bluetoothAdapter.cancelDiscovery()
            write("ready for action".toByteArray(), socket)
        }
    }

    private fun write(bytes:ByteArray, socket: BluetoothSocket) {

        socket.connect()

        try {
            socket.outputStream.write(bytes)
        } catch (e: IOException) {

        }
    }

    companion object {
        private const val uuid = "00001101-0000-1000-8000-00805F9B34FB"
    }
}