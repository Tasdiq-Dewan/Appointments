package com.tasdiq.appointments.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasdiq.appointments.databinding.FragmentAppointmentListBinding

class AppointmentListFragment : Fragment() {

    private lateinit var viewModel : MainViewModel
    private lateinit var recyclerView : RecyclerView
    private var _binding : FragmentAppointmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.loadAppointments(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppointmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.appointmentList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = AppointmentAdapter(view.context, viewModel)
        val fab = binding.addFab
        fab.setOnClickListener{
            val action = AppointmentListFragmentDirections.actionAppointmentListFragment2ToAddAppointmentFragment()
            this.findNavController().navigate(action)
        }
    }

    companion object {
        fun newInstance() = AppointmentListFragment()
    }
}