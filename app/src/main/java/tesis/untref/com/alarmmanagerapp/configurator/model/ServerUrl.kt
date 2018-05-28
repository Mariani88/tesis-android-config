package tesis.untref.com.alarmmanagerapp.configurator.model

import tesis.untref.com.alarmmanagerapp.utils.checkThat
import java.util.regex.Pattern

class ServerUrl private constructor(val ip: String, val port: Int) {

    companion object {

        private const val PATTERN = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\$"

        fun create(serverIp: String, port: Int): ServerUrl {
            checkThat(serverIp.isNotEmpty(), RuntimeException("ip can not be empty"))
            checkThat(matchWithIpPattern(serverIp), RuntimeException("invalid url"))
            checkThat(port in 1..65534, RuntimeException("Invalid port number"))
            return ServerUrl(serverIp, port)
        }

        private fun matchWithIpPattern(serverIp: String) = Pattern.matches(PATTERN, serverIp)
    }
}