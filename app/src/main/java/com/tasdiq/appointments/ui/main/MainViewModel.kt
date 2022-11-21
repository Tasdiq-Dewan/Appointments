package com.tasdiq.appointments.ui.main

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.tasdiq.appointments.model.Appointment
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class MainViewModel : ViewModel() {
    var allAppointments : MutableList<Appointment> = mutableListOf()

    fun addAppointment(newAppointment : Appointment, context: FragmentActivity){
        allAppointments.add(newAppointment)
        saveAppointments(allAppointments, context)
        allAppointments.sort()
    }

    fun saveAppointments(appointments: List<Appointment>, context: FragmentActivity) {
        val jsonString = encodeAppointments(appointments)
        val textFile = File(context.filesDir, "appointments.json")
        textFile.writeText(jsonString)
    }

    fun encodeAppointments(appointments: List<Appointment>): String {
        return Json.encodeToString(appointments)
    }

    fun loadAppointments(context : FragmentActivity){
        val textFile = File(context.filesDir, "appointments.json")
        if(textFile.isFile){
            val jsonString = textFile.readText()
            val appointmentList : MutableList<Appointment> = decodeAppointments(jsonString)
            allAppointments = appointmentList
            allAppointments.sort()
        }
    }

    fun decodeAppointments(jsonString: String): MutableList<Appointment> {
        return Json.decodeFromString<MutableList<Appointment>>(jsonString)
    }
}