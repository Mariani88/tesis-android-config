package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.util.Log

class BluetoothConnectionCreationService(private val context: Context) {

    private lateinit var broadcastReceiver: BroadcastReceiver

    fun createConnection(onSuccessfulConnection: () -> Unit, onErrorConnection: () -> Unit, onFinallyProcess: () -> Unit) {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val discoveryStarted = bluetoothAdapter.startDiscovery()
        Log.d("BT_CON_CREATION", "init discovery:$discoveryStarted")
        broadcastReceiver = DetectBluetoothBroadcastReceiver(bluetoothAdapter, onSuccessfulConnection, onErrorConnection, onFinallyProcess)
        val intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        context.registerReceiver(broadcastReceiver, intentFilter)
    }

    fun unregisterReceiver() {
        Log.d("UNREGISTERED", "Unregister broadcast receive")
        context.unregisterReceiver(broadcastReceiver)
    }
}