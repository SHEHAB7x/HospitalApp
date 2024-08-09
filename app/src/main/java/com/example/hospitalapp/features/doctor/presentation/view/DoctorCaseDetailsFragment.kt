package com.example.hospitalapp.features.doctor.presentation.view

import AdapterCaseDetails
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentDoctorCaseDetailsBinding
import com.example.hospitalapp.features.doctor.domain.model.CaseDetails
import com.example.hospitalapp.features.doctor.presentation.viewModel.DoctorCaseDetailsViewModel
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.utlis.showToast
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class DoctorCaseDetailsFragment : Fragment() {

    private var _binding: FragmentDoctorCaseDetailsBinding? = null
    private val binding get() = _binding!!
    private val caseDetailsViewModel: DoctorCaseDetailsViewModel by viewModels()
    private var caseId = 0
    private var nurseId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoctorCaseDetailsBinding.inflate(inflater, container, false)
        caseId = arguments?.getInt(Const.CASE_ID) ?: 0
        caseDetailsViewModel.getCaseDetails(caseId)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        onClicks()
    }

    private fun onClicks() {
        binding.btnAddNurse.setOnClickListener {
            val action =
                TabLayoutCaeDetailsFragmentDirections.actionTabLayoutCaeDetailsFragmentToSelectDoctorFragment(caseId,Const.NURSE)
            findNavController().navigate(action)
        }
        binding.btnRequest.setOnClickListener { showBottomSheetDialog() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observers() {
        caseDetailsViewModel.caseDetailsLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.Success -> {
                    binding.loading.visibility = View.GONE
                    setData(state.data)
                }

                is ResponseState.Error -> {
                    showToast(state.message)
                    binding.loading.visibility = View.GONE
                }

                else -> binding.loading.visibility = View.VISIBLE
            }
        }

        caseDetailsViewModel.addNurseLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.Success -> {
                    binding.loading.visibility = View.GONE
                    binding.nurse.visibility = View.VISIBLE
                    binding.nurseTxt.visibility = View.VISIBLE
                    binding.btnAddNurse.visibility = View.GONE
                    caseDetailsViewModel.getCaseDetails(caseId)
                }

                is ResponseState.Error -> {
                    showToast("Error Add nurse!")
                    Log.e("TAG", "setupObservers:${state.message} ")
                    binding.loading.visibility = View.GONE
                }

                else -> binding.loading.visibility = View.VISIBLE
            }
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>(Const.NURSE_ID)
            ?.observe(viewLifecycleOwner) { id ->
                nurseId = id
                caseDetailsViewModel.addNurse(caseId, nurseId)
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setData(caseDetails: CaseDetails) {
        binding.name.text = caseDetails.patientName
        binding.age.text = "${caseDetails.age} years"
        binding.caseDescription.text = caseDetails.description
        binding.phoneNumber.text = caseDetails.phone
        binding.doctor.text = caseDetails.doctorId

        if (caseDetails.nurseName.isEmpty()) {
            binding.nurseTxt.visibility = View.GONE
            binding.nurse.visibility = View.GONE
        } else {
            binding.btnAddNurse.visibility = View.GONE
            binding.nurse.text = caseDetails.nurseName
        }

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(caseDetails.createdAt, formatter)
        val formattedDate = date.format(DateTimeFormatter.ofPattern("dd . MM . yyyy"))
        binding.date.text = formattedDate

        if (caseDetails.status == Const.LOGOUT) {
            binding.statusImage.setImageResource(R.drawable.ic_done)
            binding.status.text = Const.LOGOUT
        } else {
            binding.statusImage.setImageResource(R.drawable.ic_pending)
            binding.status.text = Const.PENDING
        }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog()
        bottomSheetDialog.setOnMedicalTypeSelectedListener(object : BottomSheetDialog.OnMedicalTypeSelectedListener {
            override fun onMedicalTypeSelected(medicalType: String) {
                when (medicalType) {
                    Const.RECORD -> {
                        val action =
                            TabLayoutCaeDetailsFragmentDirections.actionTabLayoutCaeDetailsFragmentToSelectDoctorFragment(
                                caseId, Const.ANALYSIS
                            )
                        findNavController().navigate(action)
                    }

                    Const.MEASUREMENT -> {
                        findNavController().navigate(
                            TabLayoutCaeDetailsFragmentDirections.actionTabLayoutCaeDetailsFragmentToMedicalMeasurementFragment(
                                caseId, nurseId
                            )
                        )
                    }
                    else -> showToast("Please Select one")
                }
            }
        })
        bottomSheetDialog.show(parentFragmentManager, "BottomSheetDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}