package com.example.hospitalapp.features.hr.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.hr.domain.models.RegisterNewUser
import com.example.hospitalapp.features.hr.domain.usecase.RegisterNewUserUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewUserViewModel @Inject constructor(private val registerNewUserUseCase: RegisterNewUserUseCase) :
    ViewModel() {

    private val _userRegistrationResult = MutableLiveData<ResponseState<RegisterNewUser>>()
    val userRegistrationResult: LiveData<ResponseState<RegisterNewUser>> = _userRegistrationResult

    fun registerNewUser(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        gender: String,
        specialist: String,
        birthday: String,
        status: String,
        address: String,
        mobile: String,
        type: String
    ) {
        viewModelScope.launch {
            _userRegistrationResult.value = ResponseState.Loading
            try {
                _userRegistrationResult.value = registerNewUserUseCase.invoke(
                    email,
                    password,
                    firstName,
                    lastName,
                    gender,
                    specialist,
                    birthday,
                    status,
                    address,
                    mobile,
                    type
                )
            }catch (e : Exception){
                _userRegistrationResult.value = e.localizedMessage?.let { ResponseState.Error(it) }
            }

        }


    }
}