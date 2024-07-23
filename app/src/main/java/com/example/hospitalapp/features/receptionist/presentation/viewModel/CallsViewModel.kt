package com.example.hospitalapp.features.receptionist.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.receptionist.domain.model.AllCalls
import com.example.hospitalapp.features.receptionist.domain.usecase.CallsUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallsViewModel @Inject constructor(private val callsUseCase: CallsUseCase) : ViewModel() {
    private val _callsLiveData = MutableLiveData<ResponseState<List<AllCalls>>>()
    var callsLiveData : LiveData<ResponseState<List<AllCalls>>> = _callsLiveData

    fun getAllCalls(date :String ){
        viewModelScope.launch {
            _callsLiveData.postValue(ResponseState.Loading)
            try {
                _callsLiveData.postValue(callsUseCase.invoke(date))
            }catch (e : Exception){
                _callsLiveData.postValue(e.localizedMessage?.let { ResponseState.Error(it) })
            }
        }
    }
}