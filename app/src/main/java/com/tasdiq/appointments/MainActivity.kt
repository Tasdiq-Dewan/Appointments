package com.tasdiq.appointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tasdiq.appointments.ui.main.AddAppointmentFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AddAppointmentFragment.newInstance())
                .commitNow()
        }
    }
}