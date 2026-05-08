package com.example.hospitalapp.features.nurse.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentNurseHomeBinding
import com.example.hospitalapp.framework.database.MySharedPreferences

class NurseHomeFragment : Fragment() {
    private var _binding : FragmentNurseHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNurseHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileName.text = MySharedPreferences.getUserName()
        onClicks()
    }

    private fun onClicks() {
        binding.btnCalls.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nurseHomeFragment_to_nurseCallsFragment)
        }
        binding.profileTab.setOnClickListener {
            val id = MySharedPreferences.getUserId()
            val action = NurseHomeFragmentDirections.actionNurseHomeFragmentToProfileFragment(id)
            Navigation.findNavController(it).navigate(action)
        }
    }
}