package tesis.untref.com.alarmmanagerapp.configurator.presenter

import android.location.Location
import android.location.LocationManager
import com.google.android.gms.maps.model.LatLng
import tesis.untref.com.alarmmanagerapp.configurator.view.ConfiguratorView
import tesis.untref.com.alarmmanagerapp.location.infrastructure.LocationService

class ConfiguratorPresenter(private val configuratorView: ConfiguratorView, private val locationService: LocationService) {

    fun findLocation(locationProvider: String) {
        locationService
                .findLastKnownLocation(locationProvider)?.toGoogleMapsCoordinate()?.let { configuratorView.goLocationView(it) }
                ?: configuratorView.reportLocationNotFound()
    }

    fun setLocationProvider(locationProvider: String) {
        when (locationProvider) {
            LocationManager.NETWORK_PROVIDER -> configuratorView.configProviderToNetwork()
            LocationManager.GPS_PROVIDER -> configuratorView.configProviderToGPS()
        }
    }
}

private fun Location.toGoogleMapsCoordinate(): LatLng = LatLng(latitude, longitude)