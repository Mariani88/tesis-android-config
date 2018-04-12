package tesis.untref.com.alarmmanagerapp.location.infrastructure

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.support.v4.content.ContextCompat
import tesis.untref.com.alarmmanagerapp.location.infrastructure.listener.DefaultLocationListener

class LocationService(private val context: Context) {

    fun findLastKnownLocation(): Location? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val networkProvider = LocationManager.NETWORK_PROVIDER
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        locationManager.requestLocationUpdates(networkProvider, 30000, 50f, DefaultLocationListener(context))

        return locationManager.getLastKnownLocation(networkProvider)
    }
}