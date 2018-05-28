package tesis.untref.com.alarmmanagerapp.configurator.view

import android.content.Intent
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.android.gms.maps.model.LatLng
import tesis.untref.com.alarmmanagerapp.R
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.connection.BluetoothConnectionProvider
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.connection.BluetoothConnectionProvider.Companion.bluetoothConnection
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.BluetoothDelivery
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.DefaultConfigurationDelivery
import tesis.untref.com.alarmmanagerapp.configurator.presenter.ConfiguratorPresenter
import tesis.untref.com.alarmmanagerapp.location.infrastructure.LocationService

class ConfiguratorActivity : AppCompatActivity(), ConfiguratorView {
    private lateinit var configuratorPresenter: ConfiguratorPresenter

    private var locationProvider = NETWORK_PROVIDER
    private val bluetoothDelivery = BluetoothDelivery(bluetoothConnection!!, ObjectMapper())
    private lateinit var gpsProviderButton: RadioButton
    private lateinit var networkProviderButton: RadioButton
    private lateinit var transferLocationButton: Button
    private lateinit var phoneLocation: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configurator)
        configuratorPresenter = ConfiguratorPresenter(this,
                LocationService(this), DefaultConfigurationDelivery(bluetoothDelivery))
        gpsProviderButton = findViewById(R.id.gps_button)
        networkProviderButton = findViewById(R.id.network_button)
        transferLocationButton = findViewById(R.id.transfer_location_button)
        transferLocationButton.setOnClickListener { configuratorPresenter.sendPhoneLocation(phoneLocation) }
        val checkLocationButton = findViewById<Button>(R.id.check_location_button)
        checkLocationButton.setOnClickListener { configuratorPresenter.findLocation(locationProvider) }
        gpsProviderButton.setOnClickListener { configuratorPresenter.setLocationProvider(GPS_PROVIDER) }
        networkProviderButton.setOnClickListener { configuratorPresenter.setLocationProvider(NETWORK_PROVIDER) }

        val ssidField = findViewById<EditText>(R.id.ssid_edit_text)
        val passwordField = findViewById<EditText>(R.id.password_edit_text)
        val connectButton = findViewById<Button>(R.id.connect_button)
        connectButton.setOnClickListener { configuratorPresenter.sendWifiConnectionConfiguration(getContent(ssidField), getContent(passwordField)) }

        val serverIpField = findViewById<EditText>(R.id.server_ip_text)
        val serverPortField = findViewById<EditText>(R.id.server_port_text)
        val transferServerButton = findViewById<Button>(R.id.url_server_transfer_button)
        transferServerButton.setOnClickListener { configuratorPresenter.sendServerUrl(getContent(serverIpField), getContent(serverPortField).toInt()) }
    }

    override fun goLocationView(location: LatLng) {
        this.phoneLocation = location
        val intent = Intent(this, LocationActivity::class.java)
        intent.putExtra("location", location)
        startActivity(intent)
        transferLocationButton.isEnabled = true
    }

    override fun reportLocationNotFound() {
        transferLocationButton.isEnabled = false
        Toast.makeText(this, "Location not found, retry please", LENGTH_LONG).show()
    }

    override fun configProviderToNetwork() {
        gpsProviderButton.isChecked = false
        this.locationProvider = NETWORK_PROVIDER
    }

    override fun configProviderToGPS() {
        networkProviderButton.isChecked = false
        this.locationProvider = GPS_PROVIDER
    }

    override fun onBackPressed() {
        BluetoothConnectionProvider.bluetoothConnection!!.closeConnection()
        super.onBackPressed()
    }

    override fun reportOnView(viewMessage: String) {
        Toast.makeText(this, viewMessage, LENGTH_LONG).show()
    }

    private fun getContent(field: EditText) = field.text.toString()
}
