package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import tesis.untref.com.alarmmanagerapp.configurator.BluetoothServiceProvider
import tesis.untref.com.alarmmanagerapp.utils.TAG
import java.io.IOException

class DetectBluetoothBroadcastReceiver(private val bluetoothAdapter: BluetoothAdapter,
                                       private val onSuccessfulConnection: () -> Unit,
                                       private val onErrorConnection: () -> Unit,
                                       private val onFinallyProcess: () -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        if (BluetoothDevice.ACTION_FOUND == action) {

            Log.d(TAG, "searching device")
            //todo cada vez que encuentra un device, lo guarda en esta variable, tener en cuenta si hay varios
            val bluetoothDevice = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)

            if (bluetoothDevice.name == ALARM_BLUETOOTH_NAME) {
                Log.d(TAG, "device found")
                bluetoothAdapter.cancelDiscovery()

                try {
                    BluetoothServiceProvider.bluetoothConnectionService = BluetoothConnectionService(bluetoothDevice, uuid)
                    onSuccessfulConnection()
                } catch (e: IOException) {
                    Log.d(TAG, "error when connecting to remote device", e)
                    onErrorConnection()
                }finally {
                    onFinallyProcess()
                }
            }
        }
    }

    companion object {
        private const val ALARM_BLUETOOTH_NAME = "HC-05"
        private const val uuid = "00001101-0000-1000-8000-00805F9B34FB"
    }
}