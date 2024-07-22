package com.example.hospitalapp.features.receptionist.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.hospital.R
import com.example.hospital.databinding.FragmentReceptionistHomeBinding
import com.example.hospitalapp.framework.database.MySharedPreferences

class ReceptionistHomeFragment : Fragment() {
    private var _binding: FragmentReceptionistHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReceptionistHomeBinding.inflate(inflater, container, false)
        onClicks()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileName.text = MySharedPreferences.getUserName()
    }

    private fun onClicks() {
        binding.profileTab.setOnClickListener {
            val id: Int = MySharedPreferences.getUserId()
            val action = ReceptionistHomeFragmentDirections.actionReceptionistHomeFragmentToProfileFragment(id)
            Navigation.findNavController(it).navigate(action)

        }
        binding.btnCalls.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_receptionistHomeFragment_to_callsFragment)
        }
    }

}