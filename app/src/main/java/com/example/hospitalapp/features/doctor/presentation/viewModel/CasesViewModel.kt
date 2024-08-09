package com.example.hospitalapp.features.doctor.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.doctor.domain.model.AllCases
import com.example.hospitalapp.features.doctor.domain.usecase.AllCasesUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CasesViewModel @Inject constructor(private val allCasesUseCase: AllCasesUseCase) : ViewModel() {
    private val _getCasesLiveData = MutableLiveData<ResponseState<List<AllCases>>>()
    val getCasesLiveData: LiveData<ResponseState<List<AllCases>>> = _getCasesLiveData

    fun getAllCases(){
        viewModelScope.launch {
            _getCasesLiveData.postValue(ResponseState.Loading)
            try {
                _getCasesLiveData.postValue(allCasesUseCase.invoke())
            }catch (e : Exception){
                _getCasesLiveData.postValue(e.localizedMessage?.let { ResponseState.Error(it) })
            }
        }

    }
}