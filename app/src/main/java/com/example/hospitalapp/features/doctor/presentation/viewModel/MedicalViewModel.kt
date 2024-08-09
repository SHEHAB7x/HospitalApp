package com.example.hospitalapp.features.doctor.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.doctor.domain.usecase.MakeRequestUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicalViewModel @Inject constructor(private val makeRequestUseCase: MakeRequestUseCase) :
    ViewModel() {

    private val _makeRequestLiveData = MutableLiveData<ResponseState<Int>>()
    val makeRequestLiveData get() = _makeRequestLiveData

    fun makeRequest(caseId : Int, userId: Int, note: String, request: List<String>) {
        viewModelScope.launch {
            _makeRequestLiveData.postValue(ResponseState.Loading)
            try {
                _makeRequestLiveData.postValue(makeRequestUseCase.invoke(caseId, userId,note,request))
            } catch (e: Exception) {
                _makeRequestLiveData.postValue(e.localizedMessage?.let { ResponseState.Error(it) })
            }
        }
    }
}