package tesis.untref.com.alarmmanagerapp

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import tesis.untref.com.alarmmanagerapp.configurator.presenter.MainPresenter
import tesis.untref.com.alarmmanagerapp.configurator.view.ConfiguratorActivity
import tesis.untref.com.alarmmanagerapp.configurator.view.MainView

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
        val intent = Intent(this, ConfiguratorActivity::class.java)
        startActivity(intent)
        finish()
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