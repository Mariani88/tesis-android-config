package tesis.untref.com.alarmmanagerapp.configurator.comunication.domain

import com.fasterxml.jackson.annotation.JsonProperty
import tesis.untref.com.alarmmanagerapp.location.domain.CardinalPoint

class LongitudeMessage(

        @get:JsonProperty("degree")
        val degree: Int,

        @get:JsonProperty("minute")
        val minute: Int,

        @get:JsonProperty("second")
        val second: Double,

        @get:JsonProperty("cardinal_point")
        val cardinalPoint: CardinalPoint
)  {
}