package tesis.untref.com.alarmmanagerapp.configurator.model

class WifiNetwork private constructor(val ssid: String, val password: String) {


    companion object {
        fun create(ssid: String, password: String): WifiNetwork {

            return WifiNetwork(ssid, password)
        }
    }

}