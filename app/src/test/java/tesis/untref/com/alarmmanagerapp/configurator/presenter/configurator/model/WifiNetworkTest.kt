package tesis.untref.com.alarmmanagerapp.configurator.presenter.configurator.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.Test
import tesis.untref.com.alarmmanagerapp.configurator.model.WifiNetwork

class WifiNetworkTest {

    companion object {
        private const val EMPTY_SSID = ""
        private const val SSID_WITH_SPACES = "S sid"
        private const val A_EMPTY_PASSWORD = ""
        private const val PASSWORD_WITH_SPACES = "pass word"
        private const val A_VALID_SSID = "ssid"
        private const val A_VALID_PASSWORD = "password"
    }

    private var exception: Throwable? = null
    private lateinit var ssid: String
    private lateinit var password: String
    private lateinit var wifiNetwork: WifiNetwork

    @Test
    fun emptySsidThrowsException() {
        givenAnEmptySsid()

        whenCreateWifiNetwork()

        thenExceptionIsExpected()
    }

    @Test
    fun ssidWithSpacesThrowsException() {
        givenASsidWithSpaces()

        whenCreateWifiNetwork()

        thenExceptionIsExpected()
    }

    @Test
    fun emptyPasswordThrowsException() {
        givenAnEmptyPassword()

        whenCreateWifiNetwork()

        thenExceptionIsExpected()
    }

    @Test
    fun passwordWithSpacesThrowsException() {
        givenAPasswordWithSpaces()

        whenCreateWifiNetwork()

        thenExceptionIsExpected()
    }

    @Test
    fun validSsidAndPasswordCreateWifiNetwork() {
        givenAnSsidAndPassword()

        whenCreateWifiNetwork()

        thenCreateSuccessful()
    }

    private fun thenCreateSuccessful() {
        assertThat(wifiNetwork.ssid).isEqualTo(A_VALID_SSID)
        assertThat(wifiNetwork.password).isEqualTo(A_VALID_PASSWORD)
    }

    private fun givenAnSsidAndPassword() {
        ssid = A_VALID_SSID
        password = A_VALID_PASSWORD
    }

    private fun givenAPasswordWithSpaces() {
        ssid = A_VALID_SSID
        password = PASSWORD_WITH_SPACES
    }

    private fun whenCreateWifiNetwork() {
        exception = catchThrowable { wifiNetwork = WifiNetwork.create(ssid, password) }
    }

    private fun thenExceptionIsExpected() {
        assertThat(exception).isNotNull()
        assertThat(exception).isInstanceOf(RuntimeException::class.java)
    }

    private fun givenAnEmptySsid() {
        ssid = EMPTY_SSID
        password = A_VALID_PASSWORD
    }

    private fun givenASsidWithSpaces() {
        ssid = SSID_WITH_SPACES
        password = A_VALID_PASSWORD
    }

    private fun givenAnEmptyPassword() {
        ssid = A_VALID_SSID
        password = A_EMPTY_PASSWORD
    }
}