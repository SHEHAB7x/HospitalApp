package com.example.hospitalapp.features.doctor.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentMedicalMeasurementBinding
import com.example.hospitalapp.features.doctor.presentation.adapters.AdapterMedicalRecycler
import com.example.hospitalapp.features.doctor.presentation.viewModel.MedicalViewModel
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.utlis.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateMedicalMeasurementFragment : Fragment(),AdapterMedicalRecycler.OnItemClickListener {

    private var _binding: FragmentMedicalMeasurementBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: AdapterMedicalRecycler
    private val viewModel: MedicalViewModel by viewModels()
    private var nurseId = 0
    private var caseId = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMedicalMeasurementBinding.inflate(inflater, container, false)
        caseId = arguments?.getInt(Const.CASE_ID)!!
        nurseId = arguments?.getInt(Const.NURSE_ID)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
        observers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = AdapterMedicalRecycler(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun onClicks() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnAddMeasure.setOnClickListener {
            val txt = binding.editAddMeasure.text.toString().trim()
            if (txt.isNotEmpty()) {
                adapter.addItem(txt)
                binding.editAddMeasure.text.clear()
            }
        }
        binding.btnSend.setOnClickListener {
            binding.btnSend.setOnClickListener {
                val note = binding.editNote.text.toString().trim()
                val requestList = adapter.list
                if(caseId == 0)
                    showToast("Unknown case!")
                else if(nurseId == 0)
                    showToast("Please add Nurse first")
                else
                    viewModel.makeRequest(caseId, nurseId, note, requestList)
            }
        }
    }

    private fun observers() {
        viewModel.makeRequestLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.Success -> {
                    binding.loading.visibility = View.GONE
                    findNavController().popBackStack()
                    showToast("Request Successful!")
                }
                is ResponseState.Error -> {
                    binding.loading.visibility = View.GONE
                    showToast(state.message)
                }
                else ->
                    binding.loading.visibility = View.VISIBLE
            }
        }
    }

    override fun onCancelClicked(position: Int) {
        adapter.removeItem(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}