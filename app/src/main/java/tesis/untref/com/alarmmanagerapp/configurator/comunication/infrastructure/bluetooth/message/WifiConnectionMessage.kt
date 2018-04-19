package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.message

import com.fasterxml.jackson.annotation.JsonProperty
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.AlarmAction
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.Message

class WifiConnectionMessage(
        @get:JsonProperty("action")
        val action: AlarmAction,

        @get:JsonProperty("ssid")
        val ssid: String,

        @get:JsonProperty("password")
        val password: String): Message