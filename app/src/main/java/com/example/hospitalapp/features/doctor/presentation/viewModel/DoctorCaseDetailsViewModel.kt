package com.example.hospitalapp.features.doctor.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.doctor.domain.model.CaseDetails
import com.example.hospitalapp.features.doctor.domain.usecase.AddNurseUseCase
import com.example.hospitalapp.features.doctor.domain.usecase.CaseDetailsUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorCaseDetailsViewModel @Inject constructor(
    private val caseDetailsUseCase: CaseDetailsUseCase,
    private val addNurseUseCase: AddNurseUseCase
) : ViewModel() {
    private val _caseDetailsLiveData = MutableLiveData<ResponseState<CaseDetails>>()
    val caseDetailsLiveData get() = _caseDetailsLiveData

    fun getCaseDetails(caseId: Int) {
        viewModelScope.launch {
            _caseDetailsLiveData.postValue(ResponseState.Loading)
            try {
                _caseDetailsLiveData.postValue(caseDetailsUseCase.invoke(caseId))
            } catch (e: Exception) {
                _caseDetailsLiveData.postValue(e.localizedMessage?.let { ResponseState.Error(it) })
            }
        }
    }

    private val _addNurseLiveData = MutableLiveData<ResponseState<Int>>()
    val addNurseLiveData get() = _addNurseLiveData

    fun addNurse(caseId: Int, nurseId: Int) {
        viewModelScope.launch {
            _addNurseLiveData.postValue(ResponseState.Loading)
            try {
                _addNurseLiveData.postValue(addNurseUseCase.invoke(caseId,nurseId))
            } catch (e: Exception) {
                _addNurseLiveData.postValue(e.localizedMessage?.let { ResponseState.Error(it) })
            }
        }
    }
}