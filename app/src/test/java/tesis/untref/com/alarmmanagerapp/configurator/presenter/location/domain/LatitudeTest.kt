package tesis.untref.com.alarmmanagerapp.configurator.presenter.location.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import tesis.untref.com.alarmmanagerapp.location.domain.CardinalPoint
import tesis.untref.com.alarmmanagerapp.location.domain.Latitude

class LatitudeTest {

    private var googleMapsLatitude: Double = 0.0
    private lateinit var retrievedLatitude: Latitude

    companion object {
        private const val A_NEGATIVE_GOOGLE_MAPS_LATITUDE = -59.96
        private const val DEGREE = 59
        private const val MINUTE = 57
        private const val SECOND = 36.0
        private val CARDINAL_POINT_SOUTH = CardinalPoint.SOUTH
        private const val DELTA = 0.000001
    }

    @Test
    fun negativeLatitudeWithDecimalsShouldTransformWithSouthCardinalPoint() {
        givenANegativeGoogleMapsLatitude()

        whenCreateLatitude()

        thenRetrieveACorrectLatitude()
    }

    private fun thenRetrieveACorrectLatitude() {
        assertThat(retrievedLatitude.degree).isEqualTo(DEGREE)
        assertThat(retrievedLatitude.minute).isEqualTo(MINUTE)
        assertThat(retrievedLatitude.second).isBetween(SECOND, SECOND + DELTA)
        assertThat(retrievedLatitude.cardinalPoint).isEqualTo(CARDINAL_POINT_SOUTH)
    }

    private fun whenCreateLatitude() {
        retrievedLatitude = Latitude.create(googleMapsLatitude)
    }

    private fun givenANegativeGoogleMapsLatitude() {
        googleMapsLatitude = A_NEGATIVE_GOOGLE_MAPS_LATITUDE
    }


}