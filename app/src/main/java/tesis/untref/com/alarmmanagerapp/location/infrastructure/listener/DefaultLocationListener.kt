package tesis.untref.com.alarmmanagerapp.location.infrastructure.listener

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationProvider.OUT_OF_SERVICE
import android.location.LocationProvider.TEMPORARILY_UNAVAILABLE
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG

class DefaultLocationListener(private val context: Context) : LocationListener {

    override fun onLocationChanged(location: Location) {
        //not required
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        when (status) {
            OUT_OF_SERVICE -> showMessage("$provider out of service. Check location settings")
            TEMPORARILY_UNAVAILABLE -> showMessage("$provider temporarily unavailable")
        }
    }

    override fun onProviderEnabled(provider: String) {
        //not required
    }

    override fun onProviderDisabled(provider: String) {
        showMessage("Location by $provider failed, check location settings")
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, LENGTH_LONG).show()
    }
}