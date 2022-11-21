package com.tasdiq.appointments.model

import android.icu.text.SimpleDateFormat
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class Appointment (val task : String, val dueDate : String, val dueTime : String) : Comparable<Appointment>{

    override fun compareTo(app2 : Appointment) : Int {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val hhmm = SimpleDateFormat("hh:mm")
        val date1  : Date = sdf.parse(dueDate)
        val date2 : Date = sdf.parse(app2.dueDate)
        if(date1.compareTo(date2) == 0){
            val time1 = hhmm.parse(dueTime)
            val time2 = hhmm.parse(app2.dueTime)
            if(time1.compareTo(time2) == 0){
                return 0
            }
            else if(time1.compareTo(time2) == -1){
                return -1
            }
            return 1
        }
        else if(date1.compareTo(date2) == -1){
            return -1
        }
        return 1
    }
}