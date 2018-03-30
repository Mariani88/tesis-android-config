package tesis.untref.com.alarmmanagerapp.configurator.presenter

import android.bluetooth.BluetoothAdapter
import org.junit.Test
import org.mockito.Mockito.*
import tesis.untref.com.alarmmanagerapp.configurator.view.MainView

class MainPresenterTest {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var mainView: MainView
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var userActivatedBluetooth: Boolean? = null

    @Test
    fun reportToUserWhenIncompatibilityBluetooth() {
        givenAPresenter()
        givenIncompatibilityBluetooth()

        whenCheckBluetoothConnection()

        verify(mainView, times(1)).reportIncompatibilityBluetooth()
    }

    @Test
    fun ifBluetoothOnThenGoToNextView() {
        givenAPresenter()
        givenABluetoothState(enabled = true)

        whenCheckBluetoothConnection()

        verify(mainView, times(1)).goNextView()
    }

    @Test
    fun ifBluetoothOffThenReportToUser() {
        givenAPresenter()
        givenABluetoothState(enabled = false)

        whenCheckBluetoothConnection()

        verify(mainView, times(1)).reportBluetoothIsOff()
    }

    @Test
    fun userNotActivatedBluetoothReportToUserThatBluetoothIsRequired() {
        givenAPresenter()
        givenUserNotActivatedBluetooth()

        whenCheckUserActivatedBluetooth()

        verify(mainView, times(1)).reportBluetoothOnIsRequired()
    }

    @Test
    fun userActivatedBluetoothGoToNextView() {
        givenAPresenter()
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

    private fun givenAPresenter() {
        mainView = mock(MainView::class.java)
        mainPresenter = MainPresenter(mainView)
    }
}