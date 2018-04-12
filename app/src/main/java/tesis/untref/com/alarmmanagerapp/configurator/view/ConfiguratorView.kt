package tesis.untref.com.alarmmanagerapp.configurator.view

import com.google.android.gms.maps.model.LatLng

interface ConfiguratorView {
    fun goLocationView(location: LatLng)
    fun reportLocationNotFound()
}