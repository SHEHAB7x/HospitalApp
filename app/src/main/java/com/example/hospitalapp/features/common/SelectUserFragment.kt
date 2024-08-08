package com.example.hospitalapp.features.common

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentSelectUserBinding
import com.example.hospitalapp.features.receptionist.presentation.adapters.AdapterSelectDoctor
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.utlis.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectUserFragment : Fragment() {
    private var _binding: FragmentSelectUserBinding? = null
    private val binding get() = _binding!!
    private val selectDoctorViewModel: SelectUserViewModel by viewModels()
    private var adapterSelectDoctor: AdapterSelectDoctor? = null
    private var userId = 0
    private var userName: String? = null
    private var type: String? = null
    private var caseId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getString(Const.TYPE)
        caseId = arguments?.getInt(Const.CASE_ID)!!
        selectDoctorViewModel.getDoctors(type!!)
        adapterSelectDoctor = AdapterSelectDoctor()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectUserBinding.inflate(inflater, container, false)
        setupSearchEditText()
        setupAdapterClickListener()
        onClicks()
        return binding.root
    }

    private fun onClicks() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        binding.btnSelect.setOnClickListener { validateSelection() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleType()
        setupObservers()
    }

    private fun handleUI(title: String, hint: String) {
        binding.pageTitle.text = title
        binding.btnSelect.text = title
        binding.search.hint = hint
    }

    private fun handleType() {
        when (type) {
            Const.DOCTOR -> {
                handleUI(getString(R.string.select_doctor), getString(R.string.search_for_doctors))
            }
            Const.NURSE -> {
                handleUI(getString(R.string.select_nurse), getString(R.string.search_for_nurse))
            }
            Const.ANALYSIS ->{
                handleUI(getString(R.string.select_analysis_employee),
                    getString(R.string.search_for_analysis_employee))
            }
        }
    }

    private fun setupObservers() {
        selectDoctorViewModel.selectDoctorLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.Success -> {
                    binding.loading.visibility = View.GONE
                    adapterSelectDoctor?.list = state.data
                    binding.recyclerDoctors.adapter = adapterSelectDoctor
                }
                is ResponseState.Error -> {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
                else -> binding.loading.visibility = View.VISIBLE
            }
        }

        selectDoctorViewModel.filteredUsers.observe(viewLifecycleOwner) { filteredUsers ->
            adapterSelectDoctor?.list = filteredUsers
            adapterSelectDoctor?.notifyDataSetChanged()
        }
    }

    private fun setupSearchEditText() {
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                selectDoctorViewModel.filterUsers(s.toString())
            }
        })
    }

    private fun setupAdapterClickListener() {
        adapterSelectDoctor?.listener = object : AdapterSelectDoctor(), AdapterSelectDoctor.OnItemClickListener {
            override fun onItemClick(id: Int, name: String) {
                userId = id
                userName = name
            }
        }
    }

    private fun validateSelection() {
        if (userId == 0) {
            if (type == Const.DOCTOR)
                showToast(getString(R.string.please_select_doctor))
            else
                showToast(getString(R.string.please_select_nurse))
        } else if (type == Const.DOCTOR){
            findNavController().previousBackStackEntry?.savedStateHandle?.set(Const.DOCTOR_ID, userId)
            findNavController().previousBackStackEntry?.savedStateHandle?.set(Const.DOCTOR_NAME, userName)
            findNavController().popBackStack()
        }
        else if(type == Const.NURSE){
            findNavController().previousBackStackEntry?.savedStateHandle?.set(Const.NURSE_ID, userId)
            findNavController().previousBackStackEntry?.savedStateHandle?.set(Const.NURSE_NAME, userName)
            findNavController().popBackStack()
        }
        else if(type == Const.ANALYSIS){
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.selectDoctorFragment, true)
                .build()
            val action = SelectUserFragmentDirections.actionSelectDoctorFragmentToMedicalRecordFragment(
                caseId = caseId, analysisId = userId
            )
            findNavController().navigate(action, navOptions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
