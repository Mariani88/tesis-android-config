package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message

import com.fasterxml.jackson.annotation.JsonProperty
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.AlarmAction
import tesis.untref.com.alarmmanagerapp.location.domain.PhoneLocation

class LocationMessage(
        @get:JsonProperty("latitude")
        val latitude: LatitudeMessage,

        @get:JsonProperty("longitude")
        val longitude: LongitudeMessage
) : Message(AlarmAction.SET_LOCATION) {

    companion object {
        fun from(phoneLocation: PhoneLocation): LocationMessage {
            val latitudeMessage = LatitudeMessage.from(phoneLocation.latitude)
            val longitudeMessage = LongitudeMessage.from(phoneLocation.longitude)
            return LocationMessage(latitudeMessage, longitudeMessage)
        }
    }
}