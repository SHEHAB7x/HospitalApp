package com.example.hospitalapp.features.receptionist.presentation.view

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
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
import com.example.hospitalapp.features.receptionist.domain.model.ShowCall
import com.example.hospitalapp.features.receptionist.presentation.viewModel.CaseDetailsViewModel
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

    private fun handleLogoutBtn() {
        val newText = getString(R.string.case_has_been_logged_out)
        val originalColor = ContextCompat.getColor(requireContext(), R.color.red)
        val newColor = ContextCompat.getColor(requireContext(), R.color.light_gray)

        val textAnimator = ValueAnimator.ofInt(0, newText.length).apply {
            duration = 300
            addUpdateListener { animation ->
                val length = animation.animatedValue as Int
                binding.btnLogout.text = newText.substring(0, length)
            }
        }

        val colorAnimator = ValueAnimator.ofArgb(originalColor, newColor).apply {
            duration = 300
            addUpdateListener { animator ->
                binding.btnLogout.setBackgroundColor(animator.animatedValue as Int)
            }
        }
        val slideAnimator = ObjectAnimator.ofFloat(binding.btnLogout, "translationX", 0f, -50f).apply {
            duration = 150
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
        }

        textAnimator.start()
        colorAnimator.start()
        slideAnimator.start()

        slideAnimator.addListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                binding.btnLogout.apply {
                    text = newText
                    setBackgroundColor(newColor)
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_gray))
                    isEnabled = false
                }
            }
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}