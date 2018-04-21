package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.connection

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.util.Log
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.DetectBluetoothBroadcastReceiver
import tesis.untref.com.alarmmanagerapp.utils.TAG

open class BluetoothConnectionCreationService(private val context: Context) {

    private lateinit var broadcastReceiver: BroadcastReceiver

    open fun createConnection(onSuccessfulConnection: () -> Unit, onErrorConnection: () -> Unit, onFinallyProcess: () -> Unit) {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val discoveryStarted = bluetoothAdapter.startDiscovery()
        Log.d(TAG, "init discovery:$discoveryStarted")
        broadcastReceiver = DetectBluetoothBroadcastReceiver(bluetoothAdapter, onSuccessfulConnection, onErrorConnection, onFinallyProcess)
        val intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        context.registerReceiver(broadcastReceiver, intentFilter)
    }

    open fun unregisterReceiver() {
        Log.d(TAG, "Unregister broadcast receive")
        context.unregisterReceiver(broadcastReceiver)
    }
}