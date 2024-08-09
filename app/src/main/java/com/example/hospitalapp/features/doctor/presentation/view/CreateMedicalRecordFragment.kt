package com.example.hospitalapp.features.doctor.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentMedicalRecordBinding
import com.example.hospitalapp.features.doctor.presentation.adapters.AdapterMedicalRecycler
import com.example.hospitalapp.features.doctor.presentation.viewModel.MedicalViewModel
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.utlis.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateMedicalRecordFragment : Fragment(), AdapterMedicalRecycler.OnItemClickListener {

    private var _binding: FragmentMedicalRecordBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: AdapterMedicalRecycler
    private val viewModel: MedicalViewModel by viewModels()

    private var caseId: Int = 0
    private var analysisId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMedicalRecordBinding.inflate(inflater, container, false)
        caseId = arguments?.getInt(Const.CASE_ID) ?: 0
        analysisId = arguments?.getInt(Const.ANALYSIS_ID) ?: 0
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        onClicks()
        observers()
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
            val note = binding.editNote.text.toString().trim()
            val requestList = adapter.list
            if (caseId == 0)
                showToast("Unknown case!")
            else if (analysisId == 0)
                showToast("Please add analysis employee first")
            else
                viewModel.makeRequest(caseId, analysisId, note, requestList)
        }
    }

    private fun observers() {
        viewModel.makeRequestLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.Success -> {
                    binding.loading.visibility = View.GONE
                    showToast("Request Successful!")
                    findNavController().popBackStack()
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
