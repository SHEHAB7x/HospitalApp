package com.example.hospitalapp.features.receptionist.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentCreateCallBinding
import com.example.hospitalapp.features.receptionist.presentation.viewModel.CreateCallViewModel
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.utlis.isEmailValid
import com.example.hospitalapp.utlis.isValidPhoneNumber
import com.example.hospitalapp.utlis.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateCallFragment : Fragment() {

    private var _binding: FragmentCreateCallBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CreateCallViewModel by viewModels()
    private var doctorId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateCallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
        setupObservers()
    }

    private fun onClicks() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        binding.selectDoctor.setOnClickListener {
            val action = CreateCallFragmentDirections.actionCreateCallFragmentToSelectDoctorFragment(Const.DOCTOR)
            findNavController().navigate(action)
        }
        binding.btnSendCall.setOnClickListener { validate() }
    }

    private fun setupObservers() {
        viewModel.createCallLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.Success -> {
                    binding.loading.visibility = View.GONE
                    findNavController().navigate(R.id.action_createCallFragment_to_successfulCallFragment)
                }

                is ResponseState.Error -> {
                    binding.loading.visibility = View.GONE
                    showToast(state.message)
                    Log.e("TAG", "observers: ${state.message}")
                }

                else -> binding.loading.visibility = View.VISIBLE
            }
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(Const.DOCTOR_ID)
            ?.observe(viewLifecycleOwner) { id ->
                doctorId = id
            }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>(Const.DOCTOR_NAME)
            ?.observe(viewLifecycleOwner) { name ->
                binding.selectDoctor.text = name
            }
    }

    private fun validate() {
        val name = binding.editPatientName.text.toString()
        val age = binding.editAge.text.toString()
        val phone = binding.editPhone.text.toString()
        val description = binding.editDescription.text.toString()

        when {
            name.isEmpty() -> showToast("Enter name")
            age.isEmpty() -> showToast("Enter age")
            phone.isEmpty() -> showToast("Enter phone number")
            !phone.isValidPhoneNumber() -> showToast("Please enter valid phone number")
            else -> viewModel.createCall(name, doctorId, age, phone, description)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
