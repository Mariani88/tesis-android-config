package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message

import com.fasterxml.jackson.annotation.JsonProperty
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.AlarmAction
import tesis.untref.com.alarmmanagerapp.location.domain.PhoneLocation

class LocationMessage(
        @get:JsonProperty("latitude")
        val latitude: LatitudeDocument,

        @get:JsonProperty("longitude")
        val longitude: LongitudeDocument
) : Message(AlarmAction.SET_LOCATION) {

    companion object {
        fun from(phoneLocation: PhoneLocation): LocationMessage {
            val latitudeMessage = LatitudeDocument.from(phoneLocation.latitude)
            val longitudeMessage = LongitudeDocument.from(phoneLocation.longitude)
            return LocationMessage(latitudeMessage, longitudeMessage)
        }
    }
}