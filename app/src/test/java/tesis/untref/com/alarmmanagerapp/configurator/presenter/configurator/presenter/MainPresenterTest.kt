package tesis.untref.com.alarmmanagerapp.configurator.presenter.configurator.presenter

import android.bluetooth.BluetoothAdapter
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mockito.*
import tesis.untref.com.alarmmanagerapp.configurator.comunication.infrastructure.bluetooth.connection.BluetoothConnectionCreationService
import tesis.untref.com.alarmmanagerapp.configurator.presenter.MainPresenter
import tesis.untref.com.alarmmanagerapp.configurator.view.MainView

class MainPresenterTest {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var mainView: MainView
    private lateinit var connectionCreationService: BluetoothConnectionCreationService
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var userActivatedBluetooth: Boolean? = null

    @Before
    fun setUp() {
        bluetoothAdapter = mock(BluetoothAdapter::class.java)
        mainView = mock(MainView::class.java)
        connectionCreationService = mock(BluetoothConnectionCreationService::class.java)
        mainPresenter = MainPresenter(mainView, connectionCreationService)
    }

    @Test
    fun reportToUserWhenIncompatibilityBluetooth() {
        givenIncompatibilityBluetooth()

        whenCheckBluetoothConnection()

        verify(mainView, times(1)).reportIncompatibilityBluetooth()
    }

    @Test
    @Ignore
    fun ifBluetoothOnThenGoToNextView() {
        givenABluetoothState(enabled = true)

        whenCheckBluetoothConnection()

        verify(mainView, times(1)).goNextView()
    }

    @Test
    fun ifBluetoothOffThenReportToUser() {
        givenABluetoothState(enabled = false)

        whenCheckBluetoothConnection()

        verify(mainView, times(1)).reportBluetoothIsOff()
    }

    @Test
    fun userNotActivatedBluetoothReportToUserThatBluetoothIsRequired() {
        givenUserNotActivatedBluetooth()

        whenCheckUserActivatedBluetooth()

        verify(mainView, times(1)).reportBluetoothOnIsRequired()
    }

    @Test
    @Ignore
    fun userActivatedBluetoothGoToNextView() {
        givenUserActivatedBluetooth()

        whenCheckUserActivatedBluetooth()

        verify(mainView, times(1)).goNextView()
    }

    private fun givenUserActivatedBluetooth() {
        userActivatedBluetooth = true
    }

    private fun givenUserNotActivatedBluetooth() {
        userActivatedBluetooth = false
    }

    private fun whenCheckUserActivatedBluetooth() {
        mainPresenter.checkUserActivatedBluetooth(userActivatedBluetooth!!)
    }

    private fun givenABluetoothState(enabled: Boolean) {
        bluetoothAdapter = mock(BluetoothAdapter::class.java)
        `when`(bluetoothAdapter!!.isEnabled).thenReturn(enabled)
    }

    private fun givenIncompatibilityBluetooth() {
        bluetoothAdapter = null
    }

    private fun whenCheckBluetoothConnection() {
        mainPresenter.checkBluetoothConnection(bluetoothAdapter)
    }
}