package tesis.untref.com.alarmmanagerapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Completable
import tesis.untref.com.alarmmanagerapp.configurator.view.ConfiguratorActivity
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, ConfiguratorActivity::class.java)
        Completable.fromAction {  }
                .delay(3000L, TimeUnit.MILLISECONDS)
                .subscribe { goActivity(intent) }
    }

    private fun goActivity(intent: Intent) {
        startActivity(intent)
        finish()
    }
}
