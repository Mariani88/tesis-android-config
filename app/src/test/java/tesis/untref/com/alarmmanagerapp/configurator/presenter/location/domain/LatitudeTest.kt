package tesis.untref.com.alarmmanagerapp.configurator.presenter.location.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.Test
import tesis.untref.com.alarmmanagerapp.location.domain.CardinalPoint
import tesis.untref.com.alarmmanagerapp.location.domain.Latitude

class LatitudeTest {

    private var googleMapsLatitude: Double = 0.0
    private lateinit var retrievedLatitude: Latitude
    private lateinit var expectedCardinalPoint: CardinalPoint
    private var exception: Throwable? = null

    companion object {
        private const val A_INVALID_NEGATIVE_GOOGLE_MAPS_LATITUDE = -90.1
        private const val A_GOOGLE_MAPS_LATITUDE = 59.96
        private const val A_NEGATIVE_GOOGLE_MAPS_LATITUDE = -59.96
        private const val EXPECTED_DEGREE = 59
        private const val EXPECTED_MINUTE = 57
        private const val EXPECTED_SECOND = 36.0
        private val CARDINAL_POINT_SOUTH = CardinalPoint.SOUTH
        private const val DELTA = 0.000001
        private const val A_INVALID_GOOGLE_MAPS_LATITUDE = 90.1
    }

    @Test
    fun negativeLatitudeWithDecimalsShouldTransformWithSouthCardinalPoint() {
        givenANegativeGoogleMapsLatitude()

        whenCreateLatitude()

        thenRetrieveACorrectLatitude()
    }

    @Test
    fun latitudeWithDecimalsShouldTransformWithSouthCardinalPoint() {
        givenAGoogleMapsLatitude()

        whenCreateLatitude()

        thenRetrieveACorrectLatitude()
    }

    @Test
    fun latitudeLowerThan90NegativeThrowsException(){
        givenAInvalidNegativeGoogleMapsLatitude()

        whenCreateLatitude()

        thenExceptionIsExpected()
    }

    @Test
    fun latitudeHigherThan90ThrowsException(){
        givenAInvalidGoogleMapsLatitude()

        whenCreateLatitude()

        thenExceptionIsExpected()
    }

    private fun givenAInvalidGoogleMapsLatitude() {
        googleMapsLatitude = A_INVALID_GOOGLE_MAPS_LATITUDE
    }

    private fun thenExceptionIsExpected() {
        assertThat(exception).isNotNull()
        assertThat(exception).isInstanceOf(RuntimeException::class.java)
    }

    private fun givenAInvalidNegativeGoogleMapsLatitude() {
        googleMapsLatitude = A_INVALID_NEGATIVE_GOOGLE_MAPS_LATITUDE
    }

    private fun givenAGoogleMapsLatitude() {
        googleMapsLatitude = A_GOOGLE_MAPS_LATITUDE
        expectedCardinalPoint = CardinalPoint.NORTH
    }

    private fun thenRetrieveACorrectLatitude() {
        assertThat(exception).isNull()
        assertThat(retrievedLatitude.degree).isEqualTo(EXPECTED_DEGREE)
        assertThat(retrievedLatitude.minute).isEqualTo(EXPECTED_MINUTE)
        assertThat(retrievedLatitude.second).isBetween(EXPECTED_SECOND, EXPECTED_SECOND + DELTA)
        assertThat(retrievedLatitude.cardinalPoint).isEqualTo(expectedCardinalPoint)
    }

    private fun whenCreateLatitude() {
        exception = catchThrowable { retrievedLatitude = Latitude.create(googleMapsLatitude) }
    }

    private fun givenANegativeGoogleMapsLatitude() {
        googleMapsLatitude = A_NEGATIVE_GOOGLE_MAPS_LATITUDE
        expectedCardinalPoint = CARDINAL_POINT_SOUTH
    }
}