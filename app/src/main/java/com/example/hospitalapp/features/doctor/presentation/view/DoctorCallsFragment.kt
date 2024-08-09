package com.example.hospitalapp.features.doctor.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentDoctorCallsBinding
import com.example.hospitalapp.features.doctor.domain.model.AllCallsOfDoctor
import com.example.hospitalapp.features.doctor.presentation.adapters.AdapterDoctorCalls
import com.example.hospitalapp.features.doctor.presentation.viewModel.DoctorCallsViewModel
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.utlis.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorCallsFragment : Fragment() {
    private var _binding: FragmentDoctorCallsBinding? = null
    private val binding get() = _binding!!
    private val doctorCallsViewModel: DoctorCallsViewModel by viewModels()
    private lateinit var adapterDoctorCalls: AdapterDoctorCalls

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoctorCallsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        onClicks()
        doctorCallsViewModel.getAllCallsOfDoctor()
    }

    private fun setupRecyclerView() {
        adapterDoctorCalls = AdapterDoctorCalls(object : AdapterDoctorCalls.OnItemClick {
            override fun onAcceptClicked(id: Int) {
                doctorCallsViewModel.acceptRejectCall(id, Const.ACCEPT)
            }

            override fun onRejectClicked(id: Int) {
                doctorCallsViewModel.acceptRejectCall(id, Const.REJECT)
            }
        })
        binding.recyclerCalls.adapter = adapterDoctorCalls
    }

    private fun observeViewModel() {
        doctorCallsViewModel.getCallsOfDoctorLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResponseState.Success -> {
                    adapterDoctorCalls.submitList(result.data)
                    binding.loading.visibility = View.GONE
                }
                is ResponseState.Error -> {
                    binding.loading.visibility = View.GONE
                    showToast(result.message)
                }
                else -> binding.loading.visibility = View.VISIBLE
            }
        }

        doctorCallsViewModel.acceptRejectLiveData.observe(viewLifecycleOwner){result ->
            when(result){
                is ResponseState.Success ->{
                    doctorCallsViewModel.getAllCallsOfDoctor()
                    binding.loading.visibility = View.GONE
                }
                is ResponseState.Error -> {
                    binding.loading.visibility = View.GONE
                    showToast(Const.FAILED)
                }
                else -> binding.loading.visibility = View.VISIBLE

            }
        }
    }

    private fun onClicks() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
