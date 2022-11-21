package com.tasdiq.appointments.ui.main

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.tasdiq.appointments.databinding.FragmentAddAppointmentBinding
import com.tasdiq.appointments.model.Appointment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class AddAppointmentFragment : Fragment() {

    var currentDateTime = LocalDateTime.now()
    //val dateFormatter = DateTimeFormatter.ofPattern("hh:mm dd/MM/yyyy")
    val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
    val timeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
    companion object {
        fun newInstance() = AddAppointmentFragment()
    }

    private var _binding: FragmentAddAppointmentBinding? = null
    private val binding get() = _binding!!


    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAppointmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dateButton = binding.changeDate
        binding.dueDate.text = currentDateTime.format(dateFormatter)
        binding.dueTime.text = currentDateTime.format(timeFormatter)
        val timeButton = binding.changeTime
        val saveButton = binding.saveAppointment
        var cal = Calendar.getInstance()
        saveButton.setOnClickListener{checkAndSaveInputs(cal)}

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                binding.dueDate.text = sdf.format(cal.time)
            }
        }
        dateButton.setOnClickListener{
            DatePickerDialog(
                requireActivity(),
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        val timeSetListener = object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(
                view: TimePicker, hourOfDay: Int, minute: Int) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                cal.set(Calendar.MINUTE, minute)
                val sdf = SimpleDateFormat("HH:mm", Locale.UK).format(cal.time)
                binding.dueTime.text = sdf!!
            }
        }
        timeButton.setOnClickListener{
            TimePickerDialog(
                requireActivity(),
                2,
                timeSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    private fun checkAndSaveInputs(cal : Calendar) {
        val task = binding.inputTask.text.toString()
        val dueDate = binding.dueDate.text.toString()
        val dueTime = binding.dueTime.text.toString()
        var message = ""
        if(task != ""){
            viewModel.addAppointment(Appointment(task, dueDate, dueTime), requireActivity())
            message = "Appointment saved"
        }
        else{
            message = "Sorry - you need to fill in the task field"
        }
        if(message != ""){
            Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
        }
    }
}