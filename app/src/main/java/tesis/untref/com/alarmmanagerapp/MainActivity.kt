package tesis.untref.com.alarmmanagerapp

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import tesis.untref.com.alarmmanagerapp.configurator.presenter.MainPresenter
import tesis.untref.com.alarmmanagerapp.configurator.view.MainView
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity(), MainView {
    private val mainPresenter = MainPresenter(this)
    private lateinit var retryButton: Button
    private lateinit var reporterTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retryButton = findViewById(R.id.retry_button)
        reporterTextView = findViewById(R.id.reporter_text_view)
        mainPresenter.checkBluetoothConnection(BluetoothAdapter.getDefaultAdapter())
        retryButton.setOnClickListener { mainPresenter.checkBluetoothConnection(BluetoothAdapter.getDefaultAdapter()) }
    }

    override fun reportIncompatibilityBluetooth() {
        reporterTextView.visibility = View.VISIBLE
        reporterTextView.text = resources.getString(R.string.incompatibility_bluetooth)
    }

    override fun reportBluetoothIsOff() {
        val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        startActivityForResult(enableBluetoothIntent, MainActivity.REQUEST_ENABLE_BLUETOOTH)
    }

    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun goNextView() {
        val uuid = "00001101-0000-1000-8000-00805F9B34FB"
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        val startDiscovery = bluetoothAdapter.startDiscovery()

        var bluetoothDevice: BluetoothDevice?

        // Create a BroadcastReceiver for ACTION_FOUND
        this.broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val action = intent.action

                // When device discovery finds a
                if (BluetoothDevice.ACTION_FOUND == action) {
                    // Get the object from the Intent BluetoothDevice

                    //todo cada vez que encuentra un device, lo guarda en esta variable, tener en cuenta si hay varios
                    bluetoothDevice = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    val socket = bluetoothDevice!!.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
                    bluetoothAdapter.cancelDiscovery()
                    write("ready for action".toByteArray(), socket)
                }
            }
        }

        // Register the BroadcastReceiver
        val intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(broadcastReceiver, intentFilter)
        // Do not forget to unregister during onDestroy







        /*val intent = Intent(this, ConfiguratorActivity::class.java)
        startActivity(intent)
        finish()*/
    }

    override fun onDestroy() {
        unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }


    private fun write(bytes:ByteArray, socket: BluetoothSocket) {

        socket.connect()

        try {
            socket.outputStream.write(bytes)
        } catch (e: IOException) {

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
            mainPresenter.checkUserActivatedBluetooth(resultCode == RESULT_OK)
        }
    }

    override fun reportBluetoothOnIsRequired() {
        reporterTextView.text = resources.getString(R.string.bluetooth_on_required)
        reporterTextView.visibility = View.VISIBLE
        retryButton.visibility = View.VISIBLE
    }

    companion object {
        private const val REQUEST_ENABLE_BLUETOOTH = 1
    }
}