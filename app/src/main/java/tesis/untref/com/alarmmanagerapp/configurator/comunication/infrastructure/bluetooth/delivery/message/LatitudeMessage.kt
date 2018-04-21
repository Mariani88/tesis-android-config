package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message

import com.fasterxml.jackson.annotation.JsonProperty
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.Message
import tesis.untref.com.alarmmanagerapp.location.domain.CardinalPoint

class LatitudeMessage(

        @get:JsonProperty("degree")
        val degree: Int,

        @get:JsonProperty("minute")
        val minute: Int,

        @get:JsonProperty("second")
        val second: Double,

        @get:JsonProperty("cardinal_point")
        val cardinalPoint: CardinalPoint
) : Message