package com.example.hospitalapp.features.specialist.presentation.view

import  android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentCaseDetailsBinding
import com.example.hospitalapp.features.specialist.domain.model.ShowCall
import com.example.hospitalapp.features.specialist.presentation.viewModel.CaseDetailsViewModel
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class CaseDetailsFragment : Fragment() {
    private var _binding: FragmentCaseDetailsBinding? = null
    private val binding get() = _binding!!
    private val caseDetailsViewModel: CaseDetailsViewModel by viewModels()
    private var callId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCaseDetailsBinding.inflate(inflater, container, false)
        onClicks()
        observers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { callId = CaseDetailsFragmentArgs.fromBundle(it).callId }
        caseDetailsViewModel.showCall(callId)
    }

    private fun observers() {
        caseDetailsViewModel.showCallLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResponseState.Success -> {
                    binding.loading.visibility = View.GONE
                    setData(result.data)
                }

                is ResponseState.Error -> {
                    binding.loading.visibility = View.GONE
                    showToast(result.message)
                }

                is ResponseState.Loading -> binding.loading.visibility = View.VISIBLE
            }
        }

        caseDetailsViewModel.logoutCallLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResponseState.Success -> {
                    binding.loading.visibility = View.GONE
                    binding.statusImage.setImageResource(R.drawable.ic_done)
                    binding.status.text = getString(R.string.finished)
                    handleLogoutBtn()

                }

                is ResponseState.Error -> {
                    binding.loading.visibility = View.GONE
                    showToast(result.message)
                }

                is ResponseState.Loading -> binding.loading.visibility = View.VISIBLE
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun handleLogoutBtn(){
        binding.btnLogout.apply {
            text = getString(R.string.case_has_been_logged_out)
            setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.light_gray
                )
            )
            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_gray))
            isEnabled = false
        }
    }

    private fun setData(call: ShowCall) {
        binding.name.text = call.patientName
        binding.age.text = "${call.age} years"
        binding.caseDescription.text = call.description
        binding.phoneNumber.text = call.phone



        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(call.createdAt, formatter)
        val formattedDate = date.format(DateTimeFormatter.ofPattern("dd . MM . yyyy"))
        binding.date.text = formattedDate


        if (call.status == Const.LOGOUT) {
            binding.statusImage.setImageResource(R.drawable.ic_done)
            binding.status.text = Const.LOGOUT
            handleLogoutBtn()
        } else {
            binding.statusImage.setImageResource(R.drawable.ic_pending)
            binding.status.text = call.status
        }
    }

    private fun onClicks() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnLogout.setOnClickListener {
            caseDetailsViewModel.logoutCall(callId)
        }
    }
}