package com.example.hospitalapp.features.doctor.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.doctor.domain.model.AllCallsOfDoctor
import com.example.hospitalapp.features.doctor.domain.usecase.AcceptRejectUseCase
import com.example.hospitalapp.features.doctor.domain.usecase.AllCallsUseCase
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorCallsViewModel @Inject constructor(
    private val allCallsUseCase: AllCallsUseCase,
    private val acceptRejectUseCase: AcceptRejectUseCase
) : ViewModel() {
    private val _getCallsOfDoctorLiveData = MutableLiveData<ResponseState<List<AllCallsOfDoctor>>>()
    val getCallsOfDoctorLiveData: LiveData<ResponseState<List<AllCallsOfDoctor>>> = _getCallsOfDoctorLiveData

    private val _acceptRejectLiveData = MutableLiveData<ResponseState<String>>()
    val acceptRejectLiveData: LiveData<ResponseState<String>> = _acceptRejectLiveData

    fun getAllCallsOfDoctor() {
        viewModelScope.launch {
            _getCallsOfDoctorLiveData.postValue(ResponseState.Loading)
            try {
                _getCallsOfDoctorLiveData.postValue(allCallsUseCase.invoke())
            } catch (e: Exception) {
                _getCallsOfDoctorLiveData.postValue(e.localizedMessage?.let { ResponseState.Error(it) })
            }
        }
    }

    fun acceptRejectCall(id: Int, status: String) {
        viewModelScope.launch {
            _acceptRejectLiveData.postValue(ResponseState.Loading)
            try {
                acceptRejectUseCase.invoke(id, status)
                _acceptRejectLiveData.postValue(ResponseState.Success(Const.SUCCESS))
            } catch (e: Exception) {
                _acceptRejectLiveData.postValue(ResponseState.Error(Const.FAILED))
            }
        }
    }
}
