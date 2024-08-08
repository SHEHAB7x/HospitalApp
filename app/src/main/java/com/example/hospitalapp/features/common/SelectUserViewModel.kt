package com.example.hospitalapp.features.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.receptionist.domain.model.AllDoctors
import com.example.hospitalapp.features.receptionist.domain.usecase.SelectDoctorUseCase
import com.example.hospitalapp.framework.network.ResponseState
import com.example.hospitalapp.utlis.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectUserViewModel @Inject constructor(private val selectDoctorUseCase: SelectDoctorUseCase) : ViewModel() {
    private val _selectDoctorLiveData = MutableLiveData<ResponseState<List<AllDoctors>>>()
    var selectDoctorLiveData : LiveData<ResponseState<List<AllDoctors>>> = _selectDoctorLiveData

    private val _filteredUsers = MutableLiveData<List<AllDoctors>>()
    val filteredUsers: LiveData<List<AllDoctors>> = _filteredUsers
    private var originalList: List<AllDoctors>? = null

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

    fun filterUsers(query: String) {
        viewModelScope.launch {
            originalList = when(val response = selectDoctorUseCase.invoke(Const.DOCTOR)){
                is ResponseState.Success ->
                    response.data
                else -> null
            }

            val filteredList = originalList?.filter {
                it.first_name.contains(query, ignoreCase = true) || it.type.contains(query, ignoreCase = true)
            } ?: emptyList()
            _filteredUsers.postValue(filteredList)
        }
    }

}