package com.example.hospitalapp.features.doctor.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.doctor.domain.model.LogoutCall
import com.example.hospitalapp.features.doctor.domain.usecase.LogoutCallUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TabLayoutCaseDetailsViewModel @Inject constructor(
    private val logoutCallUseCase: LogoutCallUseCase
) : ViewModel() {

    private val _logoutCallLiveData = MutableLiveData<ResponseState<LogoutCall>>()
    var logoutCallLiveData: LiveData<ResponseState<LogoutCall>> = _logoutCallLiveData
    fun logoutCall(id: Int) {
        viewModelScope.launch {
            _logoutCallLiveData.postValue(ResponseState.Loading)
            try {
                _logoutCallLiveData.postValue(logoutCallUseCase.invoke(id))
            } catch (e: Exception) {
                _logoutCallLiveData.value = e.localizedMessage?.let { ResponseState.Error(it) }
            }
        }
    }
}