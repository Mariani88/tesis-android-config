package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message

import com.fasterxml.jackson.annotation.JsonProperty
import tesis.untref.com.alarmmanagerapp.location.domain.CardinalPoint
import tesis.untref.com.alarmmanagerapp.location.domain.Latitude

class LatitudeDocument(

        @get:JsonProperty("degree")
        val degree: Int,

        @get:JsonProperty("minute")
        val minute: Int,

        @get:JsonProperty("second")
        val second: Double,

        @get:JsonProperty("cardinal_point")
        val cardinalPoint: CardinalPoint
) {

    companion object {
        fun from(latitude: Latitude) =
                LatitudeDocument(latitude.degree, latitude.minute,
                        latitude.second, latitude.cardinalPoint)
    }
}