package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery

import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.ConfigurationDelivery
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message.LocationMessage
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message.ServerUrlMessage
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message.WifiConnectionMessage
import tesis.untref.com.alarmmanagerapp.configurator.model.ServerUrl
import tesis.untref.com.alarmmanagerapp.configurator.model.WifiNetwork
import tesis.untref.com.alarmmanagerapp.location.domain.PhoneLocation

class DefaultConfigurationDelivery(private val delivery: Delivery) : ConfigurationDelivery {

    override fun send(serverUrl: ServerUrl) {
        delivery.send(ServerUrlMessage.from(serverUrl))
    }

    override fun send(wifiNetwork: WifiNetwork) {
        delivery.send(WifiConnectionMessage.from(wifiNetwork))
    }

    override fun send(phoneLocation: PhoneLocation) {
        delivery.send(LocationMessage.from(phoneLocation))
    }
}