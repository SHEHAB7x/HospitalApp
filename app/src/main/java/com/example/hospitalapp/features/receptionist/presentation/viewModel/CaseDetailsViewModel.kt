package com.example.hospitalapp.features.receptionist.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.receptionist.domain.model.LogoutCall
import com.example.hospitalapp.features.receptionist.domain.model.ShowCall
import com.example.hospitalapp.features.receptionist.domain.usecase.LogoutCallUseCase
import com.example.hospitalapp.features.receptionist.domain.usecase.ShowCallUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaseDetailsViewModel @Inject constructor(
    private val showCallUseCase: ShowCallUseCase,
    private val logoutCallUseCase: LogoutCallUseCase
) : ViewModel() {
    private val _showCallLiveData = MutableLiveData<ResponseState<ShowCall>>()
    var showCallLiveData: LiveData<ResponseState<ShowCall>> = _showCallLiveData
    fun showCall(id: Int) {
        viewModelScope.launch {
            _showCallLiveData.postValue(ResponseState.Loading)
            try {
                _showCallLiveData.value = showCallUseCase.invoke(id)
            } catch (e: Exception) {
                _showCallLiveData.value = e.localizedMessage?.let { ResponseState.Error(it) }
            }
        }
    }

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