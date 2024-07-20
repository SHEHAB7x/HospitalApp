package com.example.hospitalapp.features.specialist.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.specialist.domain.model.CreateCall
import com.example.hospitalapp.features.specialist.domain.usecase.CreateCallUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCallViewModel @Inject constructor(private val createCallUseCase: CreateCallUseCase) : ViewModel() {
    private val _createCallLiveData = MutableLiveData<ResponseState<CreateCall>>()
    var createCallLiveData: LiveData<ResponseState<CreateCall>> = _createCallLiveData

    fun createCall(
        patientName: String,
        doctorId: Int,
        age: String,
        phone: String,
        description: String
    ){
        viewModelScope.launch {
            _createCallLiveData.postValue(ResponseState.Loading)
            try {
                _createCallLiveData.postValue(createCallUseCase.invoke(patientName, doctorId, age, phone, description))
            }catch (e : Exception){
                _createCallLiveData.value = e.message?.let { ResponseState.Error(it) }
            }
        }
    }
}