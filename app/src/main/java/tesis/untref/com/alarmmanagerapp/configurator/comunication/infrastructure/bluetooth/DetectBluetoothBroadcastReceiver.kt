package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import tesis.untref.com.alarmmanagerapp.configurator.BluetoothServiceProvider

class DetectBluetoothBroadcastReceiver(private val bluetoothAdapter: BluetoothAdapter) : BroadcastReceiver() {

    // Create a BroadcastReceiver for ACTION_FOUND

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        // When device discovery finds a
        if (BluetoothDevice.ACTION_FOUND == action) {
            // Get the object from the Intent BluetoothDevice

            //todo cada vez que encuentra un device, lo guarda en esta variable, tener en cuenta si hay varios
            val bluetoothDevice = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
            bluetoothAdapter.cancelDiscovery()

            //todo meter chequeo aca para que haga lo de abajo si encuentra el dispositivo buscado

            BluetoothServiceProvider.bluetoothConnectionService = BluetoothConnectionService(bluetoothDevice, uuid)
        }
    }

    companion object {
        private const val uuid = "00001101-0000-1000-8000-00805F9B34FB"
    }
}