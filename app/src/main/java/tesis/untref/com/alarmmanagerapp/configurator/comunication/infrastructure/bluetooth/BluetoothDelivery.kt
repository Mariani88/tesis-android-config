package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth

import com.fasterxml.jackson.databind.ObjectMapper
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.Delivery
import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.Message

class BluetoothDelivery(private val bluetoothConnection: BluetoothConnection,
                        private val objectMapper: ObjectMapper) : Delivery {

    override fun <T: Message> send(message: T) {
        val bytesMessage = objectMapper.writeValueAsBytes(message)
        bluetoothConnection.write(bytesMessage)
    }
}