package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.message

import com.fasterxml.jackson.annotation.JsonProperty
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.AlarmAction
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.Message

class LocationMessage(

        @get:JsonProperty("action")
        val action: AlarmAction,

        @get:JsonProperty("latitude")
        val latitude: LatitudeMessage,

        @get:JsonProperty("longitude")
        val longitude: LongitudeMessage
): Message