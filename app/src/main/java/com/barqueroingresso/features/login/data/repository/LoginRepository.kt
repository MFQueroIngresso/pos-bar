package com.barqueroingresso.features.login.data.repository

import kotlinx.coroutines.delay
import java.lang.Exception

class LoginRepository {
    suspend fun login(email:String, password:String) : Boolean {
        val emailTest = "matheus_11martins@outlook.com"
        val passwordTest = "123"

        delay(3000)
        val loginSuccess = emailTest == email && passwordTest == password
        if (loginSuccess) {
            return loginSuccess
        } else throw Exception("Usuário e senha incorretos")
    }
}