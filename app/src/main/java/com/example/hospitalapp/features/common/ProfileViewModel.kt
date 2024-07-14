package com.example.hospitalapp.features.common

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.hr.domain.models.UserProfile
import com.example.hospitalapp.features.hr.domain.usecase.HrProfileUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val hrUseCase: HrProfileUseCase
) : ViewModel() {
    private val _profileLiveData = MutableLiveData<ResponseState<UserProfile>>()
    val profileLiveData: LiveData<ResponseState<UserProfile>> = _profileLiveData

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getUserProfile(id: Int) {
        viewModelScope.launch {
            _profileLiveData.value = ResponseState.Loading
            try {
                when (val response = hrUseCase.invoke(id)) {
                    is ResponseState.Success -> _profileLiveData.value = response
                    is ResponseState.Error -> _profileLiveData.value = ResponseState.Error(response.message ?: "Unknown Error")
                    else -> _profileLiveData.value = ResponseState.Error("Invalid response status")
                }
            } catch (e: HttpException) {
                _profileLiveData.value = ResponseState.Error("Network error: ${e.message} ${e.message}")
            } catch (e: Exception) {
                _profileLiveData.value = ResponseState.Error("Error: ${e.localizedMessage}")
            }
        }
    }
}