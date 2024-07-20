package com.example.hospitalapp.features.common

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentProfileBinding
import com.example.hospitalapp.features.hr.domain.models.UserProfile
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.framework.database.MySharedPreferences
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var id: Int = 0
    private var from: String? = null
    private val viewModel: ProfileViewModel by viewModels()

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getInt(getString(R.string.id))!!
        from = arguments?.getString(getString(R.string.fromemplist))
        viewModel.getUserProfile(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        onClicks()
        observers()
        setData()
        return binding.root
    }

    private fun setData() {
        binding.username.text = MySharedPreferences.getUserName()
        binding.phone.text = MySharedPreferences.getUserPhone()
        binding.specialist.text = "Specialist - ${MySharedPreferences.getUserType()}"
        binding.email.text = MySharedPreferences.getUserEmail()
        binding.gender.text = MySharedPreferences.getUserGender()
        binding.address.text = MySharedPreferences.getUserAddress()
        binding.birthDate.text = MySharedPreferences.getUserBirthday()
        binding.status.text = MySharedPreferences.getUserStatus()
    }

    private fun observers() {
        viewModel.profileLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.Success -> {
                    showUserData(state.data)
                    binding.loading.visibility = View.GONE
                }

                is ResponseState.Error -> showError(state.message)
                is ResponseState.Loading -> loading()
            }
        }
    }

    private fun loading() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        binding.loading.visibility = View.GONE
    }

    private fun showUserData(user: UserProfile) {
        binding.username.text = user.firstName + " " + user.lastName
        binding.gender.text = user.gender
        binding.phone.text = user.mobile
        binding.address.text = user.address
        binding.birthDate.text = user.birthday
        binding.specialist.text = "Specialist - ${user.specialist}"
        binding.email.text = user.email
        binding.status.text = user.status
    }

    private fun onClicks() {
        binding.btnBack.setOnClickListener {
            when (MySharedPreferences.getUserType()) {
                Const.HR -> {
                    if (from == getString(R.string.fromemp)) {
                        Navigation.findNavController(it)
                            .navigate(R.id.action_profileFragment_to_employeeListFragment)
                    } else
                        findNavController().popBackStack()
                }

                Const.RECEPTIONIST -> {
                    findNavController().popBackStack()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}