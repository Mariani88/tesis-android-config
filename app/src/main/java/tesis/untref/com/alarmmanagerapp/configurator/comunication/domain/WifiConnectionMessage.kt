package tesis.untref.com.alarmmanagerapp.configurator.comunication.domain

import com.fasterxml.jackson.annotation.JsonProperty

class WifiConnectionMessage(
        @get:JsonProperty("action")
        val action: AlarmAction,

        @get:JsonProperty("ssid")
        val ssid: String,

        @get:JsonProperty("password")
        val password: String): Message