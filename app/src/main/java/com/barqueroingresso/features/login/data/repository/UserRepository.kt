package com.barqueroingresso.features.login.data.repository

import com.barqueroingresso.features.login.data.model.LoginRequest
import com.barqueroingresso.features.login.data.api.RetrofitService

class UserRepository constructor(private val retrofitService: RetrofitService) {

    fun login(loginRequest: LoginRequest) = retrofitService.login(loginRequest)

}