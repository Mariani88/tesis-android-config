package tesis.untref.com.alarmmanagerapp.location.domain

import kotlin.math.absoluteValue

data class Longitude private constructor(val degree: Int, val minute: Int, val second: Double, val cardinalPoint: CardinalPoint) {

    companion object {

        fun create(longitude: Double): Longitude {
            check(-180 <=longitude && longitude <= 180.0, {"latitude out of range -180 - 180 ($longitude)"})
            val modLatitude = longitude.absoluteValue
            val degree = modLatitude.toInt()
            val minute = ((modLatitude - degree) * 60).toInt()
            val second = (((modLatitude - degree) * 60) - minute) * 60
            val cardinalPoint = if(longitude < 0) CardinalPoint.WEST else CardinalPoint.EAST

            return Longitude(degree, minute, second, cardinalPoint)
        }
    }

}