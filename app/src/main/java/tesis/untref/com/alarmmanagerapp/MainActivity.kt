package tesis.untref.com.alarmmanagerapp

import android.Manifest
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
import io.reactivex.Completable
import tesis.untref.com.alarmmanagerapp.configurator.presenter.MainPresenter
import tesis.untref.com.alarmmanagerapp.configurator.view.ConfiguratorActivity
import tesis.untref.com.alarmmanagerapp.configurator.view.MainView
import java.io.IOException
import java.util.*
import android.Manifest.permission
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.support.v4.content.ContextCompat



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

    override fun goNextView() {

        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

        //val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN)

        val startDiscovery = bluetoothAdapter.startDiscovery()

        var bluetoothDevice: BluetoothDevice? = null

        // Create a BroadcastReceiver for ACTION_FOUND
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val action = intent.action

                // When device discovery finds a
                if (BluetoothDevice.ACTION_FOUND == action) {
                    // Get the object from the Intent BluetoothDevice

                    //todo cada vez que encuentra un device, lo guarda en esta variable, tener en cuenta si hay varios
                    bluetoothDevice = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)


                }
            }
        }

        // Register the BroadcastReceiver
        val intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(broadcastReceiver, intentFilter)
        // Do not forget to unregister during onDestroy


        //bluetoothAdapter.cancelDiscovery()
        /*val socket = bluetoothDevice!!.createRfcommSocketToServiceRecord(UUID.fromString("1234"))
        Completable
                .fromAction { write("ready for action".toByteArray(), socket) }
                .subscribe()


        val intent = Intent(this, ConfiguratorActivity::class.java)
        startActivity(intent)
        finish()
        unregisterReceiver(broadcastReceiver)*/
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