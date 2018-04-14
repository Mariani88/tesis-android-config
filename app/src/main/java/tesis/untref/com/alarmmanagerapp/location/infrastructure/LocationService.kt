package tesis.untref.com.alarmmanagerapp.location.infrastructure

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.support.v4.content.ContextCompat
import tesis.untref.com.alarmmanagerapp.location.infrastructure.listener.DefaultLocationListener

class LocationService(private val context: Context) {

    fun findLastKnownLocation(locationProvider: String): Location? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        //LocationManager.NETWORK_PROVIDER
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        locationManager.requestLocationUpdates(locationProvider, getMinTime(locationProvider), 5f, DefaultLocationListener(context))
        return locationManager.getLastKnownLocation(locationProvider)
    }

    private fun getMinTime(locationProvider: String): Long =
            if (locationProvider == LocationManager.NETWORK_PROVIDER) 30000 else 120000
}