package com.tasdiq.appointments.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tasdiq.appointments.R

class AppointmentAdapter (private val context: Context, val viewModel: MainViewModel) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>(){

    class AppointmentViewHolder(private val view: View) : RecyclerView.ViewHolder(view)
    {
        val appointmentTask : TextView = view.findViewById(R.id.task)
        val appointmentDueDateTime : TextView = view.findViewById((R.id.due_date_time))
    }

    override fun getItemCount(): Int {
        if(viewModel.allAppointments.size == 0){
            return 1
        }
        return viewModel.allAppointments.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.appointment_adapter, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        if(position == 0 && viewModel.allAppointments.size == 0){
            holder.appointmentDueDateTime.text = ""
            holder.appointmentTask.text = "No tasks/appointments have been added"
        }
        else{
            val task = viewModel.allAppointments[position].task
            val dueDate = viewModel.allAppointments[position].dueDate
            val dueTime = viewModel.allAppointments[position].dueTime
            holder.appointmentTask.text = task
            holder.appointmentDueDateTime.text = "Date: $dueDate Time: $dueTime"
        }
    }
}