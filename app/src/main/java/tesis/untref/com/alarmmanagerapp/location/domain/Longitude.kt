package tesis.untref.com.alarmmanagerapp.location.domain

data class Longitude private constructor(val degree: Int, val minute: Int, val second: Double, val cardinalPoint: CardinalPoint) {

    companion object {

        fun create(longitude: Double): Longitude? {
            return null
            //return Longitude(degree, minute, second, cardinalPoint)
        }
    }

}