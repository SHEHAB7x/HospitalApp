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
import com.example.hospital.databinding.FragmentCasesBinding
import com.example.hospital.databinding.FragmentDoctorCallsBinding
import com.example.hospitalapp.features.doctor.presentation.adapters.AdapterCases
import com.example.hospitalapp.features.doctor.presentation.viewModel.CasesViewModel
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.utlis.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CasesFragment : Fragment() {

    private var _binding: FragmentCasesBinding? = null
    private val binding get() = _binding!!
    private val casesViewModel : CasesViewModel by viewModels()
    private lateinit var adapterCases : AdapterCases

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCasesBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
        observers()
        casesViewModel.getAllCases()
    }

    private fun observers() {
        casesViewModel.getCasesLiveData.observe(viewLifecycleOwner){ result ->
            when(result){
                is ResponseState.Success ->{
                    binding.loading.visibility = View.GONE
                    adapterCases.submitList(result.data)
                    binding.recyclerCalls.adapter = adapterCases
                }
                is ResponseState.Error ->{
                    binding.loading.visibility = View.GONE
                    showToast(result.message)
                }
                else -> binding.loading.visibility = View.VISIBLE
            }
        }
    }

    private fun onClicks() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        adapterCases = AdapterCases(object : AdapterCases.OnClickListener {
            override fun onShowDetailsClicked(id: Int) {
                val action = CasesFragmentDirections.actionCasesFragmentToTabLayoutCaeDetailsFragment(id)
                findNavController().navigate(action)
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}