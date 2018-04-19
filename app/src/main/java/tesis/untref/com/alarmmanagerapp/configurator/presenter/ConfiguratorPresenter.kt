package tesis.untref.com.alarmmanagerapp.configurator.presenter

import android.location.Location
import android.location.LocationManager
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.android.gms.maps.model.LatLng
import tesis.untref.com.alarmmanagerapp.configurator.BluetoothConnectionProvider.Companion.bluetoothConnection
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.AlarmAction
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.message.WifiConnectionMessage
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.BluetoothDelivery
import tesis.untref.com.alarmmanagerapp.configurator.view.ConfiguratorView
import tesis.untref.com.alarmmanagerapp.location.infrastructure.LocationService

class ConfiguratorPresenter(private val configuratorView: ConfiguratorView, private val locationService: LocationService) {

    private val deliveryMessage = BluetoothDelivery(bluetoothConnection!!, ObjectMapper())

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

    fun sendWifiConnectionMessage(ssid: String, password: String) {
        deliveryMessage.send(WifiConnectionMessage(AlarmAction.CONNECT, ssid, password))
        configuratorView.reportMessageSent("connection message sent")
    }

    fun sendLocationConnectionMessage() {

    }
}

private fun Location.toGoogleMapsCoordinate() = LatLng(latitude, longitude)