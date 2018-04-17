package tesis.untref.com.alarmmanagerapp.configurator.comunication.domain

interface Delivery {
    fun <T: Message> send(message: T)
}