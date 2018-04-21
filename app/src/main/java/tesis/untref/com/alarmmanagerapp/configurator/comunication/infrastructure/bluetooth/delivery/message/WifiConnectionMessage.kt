package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message

import com.fasterxml.jackson.annotation.JsonProperty
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.AlarmAction
import tesis.untref.com.alarmmanagerapp.configurator.model.WifiNetwork

class WifiConnectionMessage private constructor(
        @get:JsonProperty("ssid") val ssid: String,
        @get:JsonProperty("password") val password: String) : Message(AlarmAction.CONNECT) {

    companion object {

        fun from(wifiNetwork: WifiNetwork) =
                WifiConnectionMessage(wifiNetwork.ssid, wifiNetwork.password)
    }
}