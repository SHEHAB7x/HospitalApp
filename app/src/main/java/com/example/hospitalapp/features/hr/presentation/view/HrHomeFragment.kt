package com.example.hospitalapp.features.hr.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hospital.R
import com.example.hospital.databinding.FragmentHrHomeBinding
import com.example.hospitalapp.framework.database.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HrHomeFragment : Fragment() {
    private var _binding: FragmentHrHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHrHomeBinding.inflate(inflater, container, false)
        onClicks()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileName.text = MySharedPreferences.getUserName()
    }
    private fun onClicks() {
        binding.btnEmployee.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_hrHomeFragment_to_employeeListFragment)
        }
        binding.profileTab.setOnClickListener{
            val id : Int =  MySharedPreferences.getUserId()
            val action = HrHomeFragmentDirections.actionHrHomeFragmentToProfileFragment(id)
            Navigation.findNavController(it).navigate(action)
        }
    }
}