package com.barqueroingresso.features.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barqueroingresso.features.login.data.model.LoginRequest
import com.barqueroingresso.features.login.data.model.LoginResponse
import com.barqueroingresso.features.login.data.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class LoginViewModel constructor(private val repository: UserRepository) : ViewModel() {

    val success = MutableLiveData<LoginResponse>()
    val errorMessages = MutableLiveData<String>()

    fun login(loginRequest: LoginRequest) {

        val request = repository.login(loginRequest)
        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    success.postValue(response.body())
                } else {
                    errorMessages.postValue("Não foi possível entrar. Verifique seu usuário e senha.")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                errorMessages.postValue(t.message)
            }
        })
    }
}


//    fun validateCredentials(email: String, password: String): Boolean {
//        val isEmailValid = email.isNotEmpty()
//        val isPasswordValid = password.isNotEmpty()
//        return isEmailValid && isPasswordValid
//    }
//

//
//    val repository = LoginRepository()
//
//    fun login(email: String, password: String) {
//        viewModelScope.launch {
//            try {
//                val loginSuccess = repository.login(email, password)
//                    success.postValue(loginSuccess)
//            } catch (exception:Exception) {
//                errorMessages.postValue(exception.message)
//            }
//
//        }
//    }
//}