package com.example.hospitalapp.features.hr.presentation.view

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentNewUserBinding
import com.example.hospitalapp.features.hr.presentation.viewmodel.NewUserViewModel
import com.example.hospitalapp.framework.database.MySharedPreferences
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.utlis.isEmailValid
import com.example.hospitalapp.utlis.isValidPhoneNumber
import com.example.hospitalapp.utlis.showToast
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar

@AndroidEntryPoint
class NewUserFragment : Fragment() {
    private var _binding: FragmentNewUserBinding? = null
    private val binding get() = _binding!!
    private val newUserViewModel: NewUserViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewUserBinding.inflate(inflater, container, false)
        onClicks()
        setupDropdowns()
        setupTextWatchers()
        observers()
        return binding.root
    }

    private fun observers() {
        newUserViewModel.userRegistrationResult.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is ResponseState.Success -> navigateToHome()
                is ResponseState.Error -> showError(state.message)
                else -> showLoadingIndicator()
            }
        })
    }

    private fun setupTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                with(binding) {
                    editEmail.setBackgroundResource(
                        if (editEmail.text.toString()
                                .isEmpty()
                        ) R.drawable.container_gray else R.drawable.container_green
                    )
                    editPassword.setBackgroundResource(
                        if (editPassword.text.toString()
                                .isEmpty()
                        ) R.drawable.container_gray else R.drawable.container_green
                    )
                    editFirstName.setBackgroundResource(
                        if (editFirstName.text.toString()
                                .isEmpty()
                        ) R.drawable.container_gray else R.drawable.container_green
                    )
                    editLastName.setBackgroundResource(
                        if (editLastName.text.toString()
                                .isEmpty()
                        ) R.drawable.container_gray else R.drawable.container_green
                    )
                    editPhone.setBackgroundResource(
                        if (editPhone.text.toString()
                                .isEmpty()
                        ) R.drawable.container_gray else R.drawable.container_green
                    )
                    editAddress.setBackgroundResource(
                        if (editAddress.text.toString()
                                .isEmpty()
                        ) R.drawable.container_gray else R.drawable.container_green
                    )
                }
            }
        }
        binding.editEmail.addTextChangedListener(textWatcher)
        binding.editPassword.addTextChangedListener(textWatcher)
        binding.editAddress.addTextChangedListener(textWatcher)
        binding.editLastName.addTextChangedListener(textWatcher)
        binding.editPhone.addTextChangedListener(textWatcher)
        binding.editFirstName.addTextChangedListener(textWatcher)
    }

    private fun setupAdapter(autoCompleteTextView: AutoCompleteTextView, items: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.drop_down_list_item, items)
        autoCompleteTextView.setAdapter(adapter)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onClicks() {
        binding.apply {
            dob.setOnClickListener {
                dataPicker()
            }
            btnCreateNewUser.setOnClickListener {
                validate()
            }
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            var isPasswordVisible = false
            iconEye.setOnClickListener {
                isPasswordVisible = !isPasswordVisible
                updateVisibility(isPasswordVisible)
            }
        }
    }

    private fun updateVisibility(isVisible : Boolean){
        when{
            isVisible -> {
                binding.editPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.iconEye.setImageResource(R.drawable.ic_eye_off)
            }
            else -> {
                binding.editPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.iconEye.setImageResource(R.drawable.ic_eye)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun dataPicker() {

        val builder = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Select date")
        builder.setTheme(R.style.CustomDatePickerTheme)
        val materialDatePicker = builder.build()

        materialDatePicker.show(childFragmentManager, "DATE_PICKER")

        materialDatePicker.addOnPositiveButtonClickListener { selection ->

            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val displayFormatter = DateTimeFormatter.ofPattern("dd . MM . yyyy")

            val selectedDate = Instant.ofEpochMilli(selection).atZone(ZoneId.systemDefault()).toLocalDate()
            binding.dob.text = selectedDate.format(dateFormatter)
        }
    }

    private fun validate() {
        val firstName = binding.editFirstName.text.toString()
        val lastName = binding.editLastName.text.toString()
        val gender = binding.genderAutoCompleteTextView.text.toString()
        val specialist = binding.specialistAutoCompleteTextView.text.toString()
        val birthday = binding.dob.text.toString()
        val status = binding.statusAutoCompleteTextView.text.toString()
        val phone = binding.editPhone.text.toString()
        val email = binding.editEmail.text.toString()
        val address = binding.editAddress.text.toString()
        val password = binding.editPassword.text.toString()
        val type = MySharedPreferences.getUserType()


        if (firstName.isEmpty()) binding.editFirstName.setBackgroundResource(R.drawable.container_error)
        else if (lastName.isEmpty()) binding.editLastName.setBackgroundResource(R.drawable.container_error)
        else if (gender.isEmpty()) binding.gender.setBackgroundResource(R.drawable.container_error)
        else if (specialist.isEmpty()) binding.specialist.setBackgroundResource(R.drawable.container_error)
        else if (birthday.isEmpty()) binding.dob.setBackgroundResource(R.drawable.container_error)
        else if (status.isEmpty()) binding.status.setBackgroundResource(R.drawable.container_error)
        else if (phone.isEmpty()) binding.status.setBackgroundResource(R.drawable.container_error)
        else if (email.isEmpty()) binding.editEmail.setBackgroundResource(R.drawable.container_error)
        else if (address.isEmpty()) binding.editAddress.setBackgroundResource(R.drawable.container_error)
        else if (password.isEmpty()) binding.editPassword.setBackgroundResource(R.drawable.container_error)
        else if(!email.isEmailValid()) showToast("Please Enter valid email")
        else if(!phone.isValidPhoneNumber()) showToast("Please Enter valid phone number")
        else {
            newUserViewModel.registerNewUser(
                email,
                password,
                firstName,
                lastName,
                gender,
                specialist,
                birthday,
                status,
                address,
                phone,
                type
            )
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoadingIndicator() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun navigateToHome() {
        Toast.makeText(requireContext(),"New user added successfully!",Toast.LENGTH_SHORT).show()
        binding.loading.visibility = View.GONE
        val action = R.id.action_newUserFragment_to_hrHomeFragment
        action.let {
            findNavController().navigate(it)
        }

    }

    private fun setupDropdowns() {
        val genderItems = listOf(Const.MALE, Const.FEMALE)
        val statusItems = listOf(Const.SINGLE, Const.MARRIED, Const.ENGAGED)
        val specialistItems = listOf(Const.DOCTOR, Const.HR, Const.NURSE)

        setupAdapter(binding.genderAutoCompleteTextView, genderItems)
        setupAdapter(binding.statusAutoCompleteTextView, statusItems)
        setupAdapter(binding.specialistAutoCompleteTextView, specialistItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}