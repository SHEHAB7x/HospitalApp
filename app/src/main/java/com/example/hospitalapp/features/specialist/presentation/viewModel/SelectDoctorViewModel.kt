package com.example.hospitalapp.features.specialist.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.specialist.domain.model.AllDoctors
import com.example.hospitalapp.features.specialist.domain.usecase.SelectDoctorUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectDoctorViewModel @Inject constructor(private val selectDoctorUseCase: SelectDoctorUseCase) : ViewModel() {
    private val _selectDoctorLiveData = MutableLiveData<ResponseState<List<AllDoctors>>>()
    var selectDoctorLiveData : LiveData<ResponseState<List<AllDoctors>>> = _selectDoctorLiveData

    fun getDoctors(type : String){
        viewModelScope.launch {
            _selectDoctorLiveData.postValue(ResponseState.Loading)
            try {
                _selectDoctorLiveData.postValue(selectDoctorUseCase.invoke(type))
            }catch (e : Exception){
                _selectDoctorLiveData.postValue(e.message?.let { ResponseState.Error(it) })
            }

        }
    }

}