package tesis.untref.com.alarmmanagerapp.configurator.view

import android.content.Intent
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import android.widget.Toast.LENGTH_LONG
import com.google.android.gms.maps.model.LatLng
import tesis.untref.com.alarmmanagerapp.R
import tesis.untref.com.alarmmanagerapp.configurator.presenter.ConfiguratorPresenter
import tesis.untref.com.alarmmanagerapp.location.infrastructure.LocationService

class ConfiguratorActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, ConfiguratorView {

    private lateinit var configuratorPresenter: ConfiguratorPresenter
    private var locationProvider = NETWORK_PROVIDER
    private lateinit var gpsProviderButton: RadioButton
    private lateinit var networkProviderButton: RadioButton
    private lateinit var transferLocationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configurator)
        configuratorPresenter = ConfiguratorPresenter(this, LocationService(this))


        val networks = arrayListOf("none", "network 1", "network 2")
        val networksSpinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, networks)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        networksSpinner.adapter = adapter
        networksSpinner.onItemSelectedListener = this

        gpsProviderButton = findViewById(R.id.gps_button)
        networkProviderButton = findViewById(R.id.network_button)
        transferLocationButton = findViewById(R.id.transfer_location_button)

        val checkLocationButton = findViewById<Button>(R.id.check_location_button)
        gpsProviderButton.setOnClickListener { configuratorPresenter.setLocationProvider(GPS_PROVIDER) }
        networkProviderButton.setOnClickListener { configuratorPresenter.setLocationProvider(NETWORK_PROVIDER) }
        checkLocationButton.setOnClickListener { configuratorPresenter.findLocation(locationProvider) }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun goLocationView(location: LatLng) {
        val intent = Intent(this, LocationActivity::class.java)
        intent.putExtra("location", location)
        startActivity(intent)
        transferLocationButton.isEnabled = true
    }

    override fun reportLocationNotFound() {
        transferLocationButton.isEnabled = false
        Toast.makeText(this, "Location not found, retry please", LENGTH_LONG).show()
    }

    override fun configLocationProvider(locationProvider: String) {
        when (locationProvider) {
            NETWORK_PROVIDER -> configProvider({ gpsProviderButton.isChecked = false }, NETWORK_PROVIDER)
            GPS_PROVIDER -> configProvider({ networkProviderButton.isChecked = false }, GPS_PROVIDER)
        }
    }

    private fun configProvider(uncheckRadioButton: () -> Unit, selectedProvider: String) {
        uncheckRadioButton()
        this.locationProvider = selectedProvider
    }
}