package tesis.untref.com.alarmmanagerapp.location.domain

import com.google.android.gms.maps.model.LatLng

data class PhoneLocation private constructor(val latitude: Latitude, val longitude: Longitude) {

    companion object {

        fun create(latLng: LatLng) =
                PhoneLocation(Latitude.create(latLng.latitude), Longitude.create(latLng.longitude))
    }
}