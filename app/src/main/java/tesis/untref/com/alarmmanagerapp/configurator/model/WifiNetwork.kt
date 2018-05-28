package tesis.untref.com.alarmmanagerapp.configurator.model

import tesis.untref.com.alarmmanagerapp.utils.checkThat

class WifiNetwork private constructor(val ssid: String, val password: String) {

    companion object {
        fun create(ssid: String, password: String): WifiNetwork {
            checkThat(!ssid.isEmpty(), RuntimeException("ssid can not be empty"))
            checkThat(!password.isEmpty(), RuntimeException("password can not be empty"))
            checkThat(!password.contains(" ", false),
                    RuntimeException("password can not contains spaces"))

            return WifiNetwork(ssid, password)
        }
    }
}