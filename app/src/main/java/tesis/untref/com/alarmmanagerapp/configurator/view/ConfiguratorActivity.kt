package tesis.untref.com.alarmmanagerapp.configurator.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import tesis.untref.com.alarmmanagerapp.R

import android.widget.ArrayAdapter

class ConfiguratorActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configurator)

        val networks = arrayListOf("none","network 1", "network 2")
        val networksSpinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, networks)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        networksSpinner.adapter = adapter
        networksSpinner.onItemSelectedListener = this
        //networksSpinner.prompt = "select network"
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}