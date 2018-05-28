package tesis.untref.com.alarmmanagerapp.configurator.presenter.configurator.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.Test
import tesis.untref.com.alarmmanagerapp.configurator.model.ServerUrl

class ServerUrlTest {

    private lateinit var serverUrl: ServerUrl
    private lateinit var serverIp: String
    private var exception: Throwable? = null
    private var port: Int = 0

    companion object {
        private const val VALID_IP = "192.168.1.1"
        private const val INVALID_IP = "192.1.1"
        private const val VALID_PORT = 8080
        private const val INVALID_LOWER_PORT = 0
        private const val INVALID_HIGH_PORT = 65535
    }

    @Test
    fun invalidIpThrowsException(){
        givenAnIp(INVALID_IP)
        givenAPort(VALID_PORT)

        whenCreateServerUrl()

        thenThrowsException()
    }

    @Test
    fun emptyIpThrowsException(){
        givenAnIp("")
        givenAPort(VALID_PORT)

        whenCreateServerUrl()

        thenThrowsException()
    }

    @Test
    fun invalidLowerPortThrowsException(){
        givenAnIp(VALID_IP)
        givenAPort(INVALID_LOWER_PORT)

        whenCreateServerUrl()

        thenThrowsException()
    }

    @Test
    fun invalidHighPortThrowsException(){
        givenAnIp(VALID_IP)
        givenAPort(INVALID_HIGH_PORT)

        whenCreateServerUrl()

        thenThrowsException()
    }

    @Test
    fun validIpAndPortCreateUrl(){
        givenAnIp(VALID_IP)
        givenAPort(VALID_PORT)

        whenCreateServerUrl()

        thenCreateCorrectly()
    }

    private fun whenCreateServerUrl() {
        exception = catchThrowable { serverUrl = ServerUrl.create(serverIp, port) }
    }

    private fun thenThrowsException(){
        assertThat(exception).isNotNull()
        assertThat(exception).isInstanceOf(RuntimeException::class.java)
    }

    private fun thenCreateCorrectly(){
        assertThat(exception).isNull()
        assertThat(serverUrl.ip).isEqualTo(VALID_IP)
        assertThat(serverUrl.port).isEqualTo(VALID_PORT)
    }

    private fun givenAPort(port: Int) {
        this.port = port
    }

    private fun givenAnIp(ip: String) {
        this.serverIp = ip
    }
}