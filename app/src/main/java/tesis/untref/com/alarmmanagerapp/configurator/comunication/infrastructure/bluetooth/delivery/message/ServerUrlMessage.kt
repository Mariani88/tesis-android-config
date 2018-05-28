package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message

import com.fasterxml.jackson.annotation.JsonProperty
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.AlarmAction
import tesis.untref.com.alarmmanagerapp.configurator.model.ServerUrl

class ServerUrlMessage private constructor(
        @get:JsonProperty("ip") val ip: String,
        @get:JsonProperty("port") val port: Int
) : Message(AlarmAction.SET_SERVER_URL) {

    companion object {

        fun from(serverUrl: ServerUrl) = ServerUrlMessage(serverUrl.ip, serverUrl.port)
    }
}