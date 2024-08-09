package com.example.hospitalapp.features.doctor.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hospital.R
import com.example.hospital.databinding.FragmentDoctorHomeBinding
import com.example.hospitalapp.framework.database.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint

class DoctorHomeFragment : Fragment() {
    private var _binding: FragmentDoctorHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoctorHomeBinding.inflate(inflater, container, false)
        onClicks()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileName.text = MySharedPreferences.getUserName()
    }

    private fun onClicks() {
        binding.profileTab.setOnClickListener{
            val id = MySharedPreferences.getUserId()
            val action = DoctorHomeFragmentDirections.actionDoctorHomeFragmentToProfileFragment(id)
            Navigation.findNavController(it).navigate(action)
        }
        binding.btnCalls.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_doctorHomeFragment_to_doctorCallsFragment)
        }
        binding.btnCases.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_doctorHomeFragment_to_casesFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}