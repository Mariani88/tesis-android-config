package tesis.untref.com.alarmmanagerapp.configurator.comunication.domain

import tesis.untref.com.alarmmanagerapp.configurator.model.ServerUrl
import tesis.untref.com.alarmmanagerapp.configurator.model.WifiNetwork
import tesis.untref.com.alarmmanagerapp.location.domain.PhoneLocation

interface ConfigurationDelivery {

    fun send(wifiNetwork: WifiNetwork)
    fun send(phoneLocation: PhoneLocation)
    fun send(serverUrl: ServerUrl)
}