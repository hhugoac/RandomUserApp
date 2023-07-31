package com.example.randomuser.userscreen

import UserData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomuser.data.ApiResponseStatus
import kotlinx.coroutines.launch

class UserViewModel : ViewModel(){

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData>
        get() = _userData

    private val _status = MutableLiveData<ApiResponseStatus<Any>>()
    val status: LiveData<ApiResponseStatus<Any>>
        get() = _status

    private val userRepository = UserRepository()

    init {
        downloadUserData()
    }

    @Suppress("UNCHECKED_CAST")
    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<UserData>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            _userData.value = apiResponseStatus.data!!
        }

        _status.value = apiResponseStatus as ApiResponseStatus<Any>
    }
    private fun downloadUserData() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            handleResponseStatus(userRepository.downloadUser())
        }
    }


}