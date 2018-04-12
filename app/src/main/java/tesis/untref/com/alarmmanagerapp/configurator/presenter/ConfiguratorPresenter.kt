package tesis.untref.com.alarmmanagerapp.configurator.presenter

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import tesis.untref.com.alarmmanagerapp.configurator.view.ConfiguratorView
import tesis.untref.com.alarmmanagerapp.location.infrastructure.LocationService

class ConfiguratorPresenter(private val configuratorView: ConfiguratorView, private val locationService: LocationService) {

    fun findLocation() {
        val location = locationService
                .findLastKnownLocation()?.toGoogleMapsCoordinate()?.
                        let { configuratorView.goLocationView(it) } ?: configuratorView.reportLocationNotFound()
    }
}

private fun Location.toGoogleMapsCoordinate(): LatLng = LatLng(latitude, longitude)