package tesis.untref.com.alarmmanagerapp.configurator.view

import android.content.Intent
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

        val checkLocationButton = findViewById<Button>(R.id.check_location_button)
        checkLocationButton.setOnClickListener { configuratorPresenter.findLocation() }


    }



    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun goLocationView(location: LatLng) {
        val intent = Intent(this, LocationActivity::class.java)
        intent.putExtra("location", location)
        startActivity(intent)

    }

    override fun reportLocationNotFound() {
        Toast.makeText(this, "Location not found, retry please", LENGTH_LONG).show()
    }
}
