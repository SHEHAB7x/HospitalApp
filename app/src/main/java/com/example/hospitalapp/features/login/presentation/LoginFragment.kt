package com.example.hospitalapp.features.login.presentation

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentLoginBinding
import com.example.hospitalapp.features.login.domain.models.User
import com.example.hospitalapp.utlis.Const
import com.example.hospitalapp.framework.network.ResponseState

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        underlineText(binding.btnForgetPassword, getString(R.string.forget_password))
        setupTextWatchers()
        onClicks()
        observeLoginResult()
        return binding.root
    }

    private fun observeLoginResult() {
        loginViewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.Success -> navigateToHome(state.data)
                is ResponseState.Error -> {
                    showError(state.message)
                    Log.e("TAG", "observeLoginResult: ${state.message}")
                }
                ResponseState.Loading -> showLoadingIndicator()
            }
        }
    }

    private fun showError(message: String) {
        binding.loading.visibility = View.GONE
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoadingIndicator() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun navigateToHome(user: User) {
        binding.loading.visibility = View.GONE
        val action = when (user.type) {
            Const.HR -> R.id.action_loginFragment_to_hrHomeFragment
            Const.RECEPTIONIST -> R.id.action_loginFragment_to_receptionistHomeFragment
            else -> null
        }
        action?.let { findNavController().navigate(it) } ?: run {
            Toast.makeText(requireContext(), "User Type )': ", Toast.LENGTH_SHORT).show()
        }
    }


    private fun underlineText(textView: TextView, content: String) {
        textView.text = SpannableString(content).apply {
            setSpan(UnderlineSpan(), 0, content.length, 0)

        }
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
                }
            }
        }
        binding.editEmail.addTextChangedListener(textWatcher)
        binding.editPassword.addTextChangedListener(textWatcher)
    }

    private fun onClicks() {
        var isPasswordVisible = false
        binding.btnLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            validate(email, password)
        }
        binding.iconEye.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            updatePasswordVisibility(isPasswordVisible)
        }
    }

    private fun updatePasswordVisibility(isVisible: Boolean) {
        when {
            isVisible -> {
                binding.editPassword.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.iconEye.setImageResource(R.drawable.ic_eye_off)
            }
            else -> {
                binding.editPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.iconEye.setImageResource(R.drawable.ic_eye)
            }
        }
        binding.editPassword.setSelection(binding.editPassword.text.length)
    }

    private fun validate(email: String, password: String) {
        if (email.isEmpty()) binding.editEmail.setBackgroundResource(R.drawable.container_error)
        else if (password.isEmpty()) binding.editPassword.setBackgroundResource(R.drawable.container_error)
        else {
            loginViewModel.loginUser(email, password)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
