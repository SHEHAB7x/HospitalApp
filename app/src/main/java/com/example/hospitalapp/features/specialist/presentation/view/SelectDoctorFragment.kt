package com.example.hospitalapp.features.specialist.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentSelectDoctorBinding
import com.example.hospitalapp.features.specialist.presentation.adapters.AdapterSelectDoctor
import com.example.hospitalapp.features.specialist.presentation.viewModel.SelectDoctorViewModel
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectDoctorFragment : Fragment() {
    private var _binding: FragmentSelectDoctorBinding? = null
    private val binding get() = _binding!!
    private var adapterSelectDoctor: AdapterSelectDoctor? = null
    private val selectDoctorViewModel: SelectDoctorViewModel by viewModels()
    var doctorId = 0
    var doctorName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectDoctorViewModel.getDoctors(Const.DOCTOR)
        adapterSelectDoctor = AdapterSelectDoctor()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectDoctorBinding.inflate(inflater, container, false)
        onClicks()
        setupSearchEditText()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    }

    private fun observers() {
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

                ResponseState.Loading -> binding.loading.visibility = View.VISIBLE
            }
        }
    }

    private fun setupSearchEditText() {
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                //selectDoctorViewModel.filterUsers(s.toString())
            }
        })
    }

    private fun onClicks() {
        adapterSelectDoctor?.listener = object : AdapterSelectDoctor(),
            AdapterSelectDoctor.OnItemClickListener {
            override fun onItemClick(id: Int, name: String) {
                doctorId = id
                doctorName = name
            }
        }
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.btnSelect.setOnClickListener{
            validate()
        }
    }

     private fun validate(){
        if (doctorId == 0)
            Toast.makeText(requireContext(), getString(R.string.please_select_doctor), Toast.LENGTH_SHORT).show()
        else
        {
            findNavController().previousBackStackEntry?.savedStateHandle?.set(Const.DOCTOR_ID,doctorId)
            findNavController().previousBackStackEntry?.savedStateHandle?.set(Const.DOCTOR_NAME,doctorName)
            findNavController().popBackStack()
        }

    }
}