package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery

import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message.Message

interface Delivery {
    fun <T: Message> send(message: T)
}