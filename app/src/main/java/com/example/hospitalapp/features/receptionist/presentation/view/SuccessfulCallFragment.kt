package com.example.hospitalapp.features.receptionist.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hospital.R
import com.example.hospital.databinding.FragmentSuccessfulCallBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessfulCallFragment : Fragment() {
    private var _binding : FragmentSuccessfulCallBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuccessfulCallBinding.inflate(inflater,container,false)
        onClicks()
        return binding.root
    }

    private fun onClicks() {
        binding.btnBackToHome.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_successfulCallFragment_to_receptionistHomeFragment)
        }
    }
}