package com.example.hospitalapp.features.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.login.domain.models.User
import com.example.hospitalapp.features.login.domain.usecase.LoginUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase
) : ViewModel() {
    private val _loginState = MutableLiveData<ResponseState<User>>()
    val loginState: LiveData<ResponseState<User>> = _loginState

    fun loginUser(email: String, password: String) {
        _loginState.value = ResponseState.Loading
        viewModelScope.launch {
            try {
                when(val response = useCase.invoke(email, password, "deviceToken")){
                    is ResponseState.Success -> {
                        _loginState.value = response
                    }
                    else -> _loginState.value = response
                }
            } catch (e: Exception) {
                _loginState.value = e.localizedMessage?.let { ResponseState.Error(it) }
            }
        }
    }

}