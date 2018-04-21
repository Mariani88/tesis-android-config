package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery

import com.fasterxml.jackson.databind.ObjectMapper
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.connection.BluetoothConnection
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message.Message

class BluetoothDelivery(private val bluetoothConnection: BluetoothConnection,
                        private val objectMapper: ObjectMapper) : Delivery {

    override fun <T: Message> send(message: T) {
        val bytes = objectMapper.writeValueAsBytes(message)
        bluetoothConnection.write(bytes)
    }
}