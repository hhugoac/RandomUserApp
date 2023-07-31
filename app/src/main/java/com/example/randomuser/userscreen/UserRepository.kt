package com.example.randomuser.userscreen

import UserData
import com.example.randomuser.data.ApiResponseStatus
import com.example.randomuser.data.UserApi.retrofitService
import com.example.randomuser.data.makeNetworkCall

class UserRepository {
    suspend fun downloadUser(): ApiResponseStatus<UserData> = makeNetworkCall {
        val dogListApiResponse = retrofitService.getAllUsers()
        dogListApiResponse
    }
}