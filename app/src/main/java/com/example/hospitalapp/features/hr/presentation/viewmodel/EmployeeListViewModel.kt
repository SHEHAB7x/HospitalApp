package com.example.hospitalapp.features.hr.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalapp.features.hr.domain.models.User
import com.example.hospitalapp.features.hr.domain.usecase.AllUsersByTypeUseCase
import com.example.hospitalapp.framework.network.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    private val allUsersByTypeUseCase: AllUsersByTypeUseCase
) : ViewModel() {
    private val _employeeListLiveData = MutableLiveData<ResponseState<List<User>>>()
    val employeeListLiveData: LiveData<ResponseState<List<User>>> = _employeeListLiveData
    private var originalList: List<User>? = null

    fun getUsers(type: String) {
        viewModelScope.launch {
            _employeeListLiveData.value = ResponseState.Loading
            try {
                _employeeListLiveData.value = allUsersByTypeUseCase.invoke(type)
            } catch (e: Exception) {
                _employeeListLiveData.value = e.localizedMessage?.let { ResponseState.Error(it) }
            }
        }
    }

    private val _filteredUsers = MutableLiveData<List<User>>()
    val filteredUsers: LiveData<List<User>> = _filteredUsers

    fun filterUsers(query: String) {
        viewModelScope.launch {
            originalList = when(val response = allUsersByTypeUseCase.invoke("All")){
                is ResponseState.Success ->
                    response.data

                else -> null
            }

             val filteredList = originalList?.filter {
                it.firstName.contains(query, ignoreCase = true) || it.type.contains(query, ignoreCase = true)
            } ?: emptyList()
            _filteredUsers.postValue(filteredList)
        }
    }
}