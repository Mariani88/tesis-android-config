package tesis.untref.com.alarmmanagerapp.location.domain

import kotlin.math.absoluteValue

data class Latitude private constructor(val degree: Int, val minute: Int, val second: Double, val cardinalPoint: CardinalPoint) {

    companion object {

        fun create(latitude: Double): Latitude {
            val modLatitude = latitude.absoluteValue
            val degree = modLatitude.toInt()
            val minute = ((modLatitude - degree) * 60).toInt()
            val second = (((modLatitude - degree) * 60) - minute) * 60
            val cardinalPoint = if(latitude < 0) CardinalPoint.SOUTH else CardinalPoint.NORTH

            return Latitude(degree, minute, second, cardinalPoint)
        }
    }

}