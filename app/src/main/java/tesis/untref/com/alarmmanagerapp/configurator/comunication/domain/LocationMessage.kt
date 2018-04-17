package tesis.untref.com.alarmmanagerapp.configurator.comunication.domain

import com.fasterxml.jackson.annotation.JsonProperty

class LocationMessage(

        @get:JsonProperty("action")
        val action: AlarmAction,

        @get:JsonProperty("latitude")
        val latitude: LatitudeMessage,

        @get:JsonProperty("longitude")
        val longitude: LongitudeMessage
): Message