package tesis.untref.com.alarmmanagerapp.configurator.view

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import tesis.untref.com.alarmmanagerapp.R

class ConfiguratorActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, ConfiguratorView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configurator)

        val networks = arrayListOf("none", "network 1", "network 2")
        val networksSpinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, networks)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        networksSpinner.adapter = adapter
        networksSpinner.onItemSelectedListener = this

        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val networkProvider = LocationManager.NETWORK_PROVIDER
        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        locationManager.requestLocationUpdates(networkProvider, 30000, 50f, object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProviderEnabled(provider: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProviderDisabled(provider: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


        val lastKnownLocation = locationManager.getLastKnownLocation(networkProvider)


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}