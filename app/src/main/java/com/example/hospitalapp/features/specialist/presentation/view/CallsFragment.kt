package com.example.hospitalapp.features.specialist.presentation.view

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentCallsBinding
import com.example.hospitalapp.features.specialist.presentation.adapters.AdapterCalls
import com.example.hospitalapp.features.specialist.presentation.viewModel.CallsViewModel
import com.example.hospitalapp.framework.network.ResponseState
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class CallsFragment : Fragment() {

    private var _binding: FragmentCallsBinding? = null
    private val binding get() = _binding!!
    private val callsViewModel: CallsViewModel by viewModels()
    private var adapterCalls: AdapterCalls? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callsViewModel.getAllCalls("")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCallsBinding.inflate(inflater, container, false)
        adapterCalls = AdapterCalls()
        onClicks()
        observers()
        return binding.root
    }

    private fun observers() {
        callsViewModel.callsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseState.Success -> {
                    binding.loading.visibility = View.GONE
                    adapterCalls?.list = it.data
                    binding.recyclerCalls.adapter = adapterCalls
                    adapterCalls?.notifyDataSetChanged()
                }

                is ResponseState.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.loading.visibility = View.GONE
                }

                is ResponseState.Loading -> binding.loading.visibility = View.VISIBLE
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onClicks() {
        binding.btnDate.setOnClickListener {
            datePicker()
        }
        binding.btnAddCall.setOnClickListener {
           Navigation.findNavController(it)
                .navigate(R.id.action_callsFragment_to_createCallFragment)
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        adapterCalls?.listener = object : AdapterCalls(),AdapterCalls.OnItemClickListener{
            override fun onItemClicked(id: Int) {
                val action = CallsFragmentDirections.actionCallsFragmentToCaseDetailsFragment(id)
                Navigation.findNavController(requireView()).navigate(action)
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun datePicker() {
        val builder = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Select date")
        builder.setTheme(R.style.CustomDatePickerTheme)
        val materialDatePicker = builder.build()

        materialDatePicker.show(childFragmentManager, "DATE_PICKER")

        materialDatePicker.addOnPositiveButtonClickListener { selection ->

            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val displayFormatter = DateTimeFormatter.ofPattern("dd . MM . yyyy")

            val selectedDate =
                Instant.ofEpochMilli(selection).atZone(ZoneId.systemDefault()).toLocalDate()

            callsViewModel.getAllCalls(selectedDate.format(dateFormatter))
            binding.dateOfCall.text = selectedDate.format(displayFormatter)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}