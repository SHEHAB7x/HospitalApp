package com.example.hospitalapp.features.doctor.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hospital.R
import com.example.hospital.databinding.FragmentMedicalMeasurementDetialsBinding
import com.example.hospital.databinding.FragmentMedicalRecordDetialsBinding
import com.example.hospitalapp.features.doctor.domain.model.CaseDetails
import com.example.hospitalapp.features.doctor.presentation.viewModel.DoctorCaseDetailsViewModel
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.utlis.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicalRecordDetailsFragment : Fragment() {

    private var _binding : FragmentMedicalRecordDetialsBinding?=null
    private val binding get() = _binding
    private val caseDetailsViewModel: DoctorCaseDetailsViewModel by viewModels()
    private var caseId = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMedicalRecordDetialsBinding.inflate(inflater,container,false)
        caseId = arguments?.getInt(Const.CASE_ID)!!
        caseDetailsViewModel.getCaseDetails(caseId)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    }

    private fun observers() {
        caseDetailsViewModel.caseDetailsLiveData.observe(viewLifecycleOwner){ state->
            when(state){
                is ResponseState.Success -> {
                    setData(state.data)
                }
                is ResponseState.Error ->
                    showToast(state.message)
                else ->{

                }
            }
        }
    }

    private fun setData(case: CaseDetails) {
        binding?.profileName?.text = case.analystName
        binding?.caseDescription?.text = case.medicalRecordNote
    }

}