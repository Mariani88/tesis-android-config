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
import tesis.untref.com.alarmmanagerapp.location.infrastructure.LocationService

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


        LocationService(this).findLastKnownLocation()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}