package tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.delivery.message

import tesis.untref.com.alarmmanagerapp.configurator.comunication.domain.AlarmAction

class StopAlertMessage:Message(AlarmAction.STOP_ALERT) {

    companion object {
        fun create() = StopAlertMessage()
    }
}