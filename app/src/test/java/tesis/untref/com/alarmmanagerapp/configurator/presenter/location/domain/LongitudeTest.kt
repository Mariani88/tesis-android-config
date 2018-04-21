package tesis.untref.com.alarmmanagerapp.configurator.presenter.location.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import tesis.untref.com.alarmmanagerapp.location.domain.CardinalPoint
import tesis.untref.com.alarmmanagerapp.location.domain.Longitude

class LongitudeTest {

    private var googleMapsLongitude: Double = 0.0
    private lateinit var retrievedLongitude: Longitude
    private lateinit var expectedCardinalPoint: CardinalPoint
    private var exception: Throwable? = null

    companion object {
        private const val A_INVALID_NEGATIVE_GOOGLE_MAPS_LONGITUDE = -180.1
        private const val A_GOOGLE_MAPS_LONGITUDE = 159.96
        private const val A_NEGATIVE_GOOGLE_MAPS_LONGITUDE = -159.96
        private const val EXPECTED_DEGREE = 159
        private const val EXPECTED_MINUTE = 57
        private const val EXPECTED_SECOND = 36.0
        private val CARDINAL_POINT_WEST = CardinalPoint.WEST
        private const val DELTA = 0.000001
        private const val A_INVALID_GOOGLE_MAPS_LONGITUDE = 180.1
    }

    @Test
    fun negativeLongitudeWithDecimalsShouldTransformWithSouthCardinalPoint() {
        givenANegativeGoogleMapsLongitude()

        whenCreateLongitude()

        thenRetrieveACorrectLongitude()
    }

    @Test
    fun longitudeWithDecimalsShouldTransformWithSouthCardinalPoint() {
        givenAGoogleMapsLongitude()

        whenCreateLongitude()

        thenRetrieveACorrectLongitude()
    }

    @Test
    fun longitudeLowerThan90NegativeThrowsException(){
        givenAInvalidNegativeGoogleMapsLongitude()

        whenCreateLongitude()

        thenExceptionIsExpected()
    }

    @Test
    fun longitudeHigherThan90ThrowsException(){
        givenAInvalidGoogleMapsLongitude()

        whenCreateLongitude()

        thenExceptionIsExpected()
    }

    private fun givenAInvalidGoogleMapsLongitude() {
        googleMapsLongitude = A_INVALID_GOOGLE_MAPS_LONGITUDE
    }

    private fun thenExceptionIsExpected() {
        Assertions.assertThat(exception).isNotNull()
        Assertions.assertThat(exception).isInstanceOf(RuntimeException::class.java)
    }

    private fun givenAInvalidNegativeGoogleMapsLongitude() {
        googleMapsLongitude = A_INVALID_NEGATIVE_GOOGLE_MAPS_LONGITUDE
    }

    private fun givenAGoogleMapsLongitude() {
        googleMapsLongitude = A_GOOGLE_MAPS_LONGITUDE
        expectedCardinalPoint = CardinalPoint.EAST
    }

    private fun thenRetrieveACorrectLongitude() {
        assertThat(exception).isNull()
        assertThat(retrievedLongitude.degree).isEqualTo(EXPECTED_DEGREE)
        assertThat(retrievedLongitude.minute).isEqualTo(EXPECTED_MINUTE)
        assertThat(retrievedLongitude.second).isBetween(EXPECTED_SECOND, EXPECTED_SECOND + DELTA)
        assertThat(retrievedLongitude.cardinalPoint).isEqualTo(expectedCardinalPoint)
    }

    private fun whenCreateLongitude() {
        exception = Assertions.catchThrowable { retrievedLongitude = Longitude.create(googleMapsLongitude) }
    }

    private fun givenANegativeGoogleMapsLongitude() {
        googleMapsLongitude = A_NEGATIVE_GOOGLE_MAPS_LONGITUDE
        expectedCardinalPoint = CARDINAL_POINT_WEST
    }
}