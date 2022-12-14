package com.barqueroingresso.features.login.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barqueroingresso.features.login.data.repository.LoginRepository
import com.barqueroingresso.util.isValidEmail
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    fun validateCredentials(email: String, password: String): Boolean {
        val isEmailValid = email.isValidEmail()
        val isPasswordValid = password.isNotEmpty()
        return isEmailValid && isPasswordValid
    }

    val success = MutableLiveData<Boolean>()
    val errorMessages = MutableLiveData<String>()

    val repository = LoginRepository()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val loginSuccess = repository.login(email, password)
                    success.postValue(loginSuccess)
            } catch (exception:Exception) {
                errorMessages.postValue(exception.message)
            }

        }
    }
}