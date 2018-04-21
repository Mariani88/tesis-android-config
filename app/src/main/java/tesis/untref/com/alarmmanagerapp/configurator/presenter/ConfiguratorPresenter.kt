package tesis.untref.com.alarmmanagerapp.configurator.presenter

import android.location.Location
import android.location.LocationManager
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.ConfigurationDelivery
import tesis.untref.com.alarmmanagerapp.configurator.model.WifiNetwork
import tesis.untref.com.alarmmanagerapp.configurator.view.ConfiguratorView
import tesis.untref.com.alarmmanagerapp.location.domain.PhoneLocation
import tesis.untref.com.alarmmanagerapp.location.infrastructure.LocationService

class ConfiguratorPresenter(private val configuratorView: ConfiguratorView,
                            private val locationService: LocationService,
                            private val configurationDelivery: ConfigurationDelivery) {

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

    fun sendWifiConnectionConfiguration(ssid: String, password: String) {
        Observable
                .just(WifiNetwork.create(ssid, password))
                .doOnNext{ configurationDelivery.send(it) }
                .doOnError{ configuratorView.reportOnView("configuration not sent, check it") }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ configuratorView.reportOnView("connection message sent") })
    }

    fun sendPhoneLocation(location: LatLng) {
        Single
                .just(PhoneLocation.create(location))
                .doOnSuccess{configurationDelivery.send(it)}
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ configuratorView.reportOnView("location sent") },
                        {configuratorView.reportOnView("error to send location")})
    }
}

private fun Location.toGoogleMapsCoordinate() = LatLng(latitude, longitude)