package com.barqueroingresso.util

object Validator {

    fun validateEmail(email: String): Boolean {
        return !(email.isEmpty() || email.isBlank() || !email.contains("@"))
    }

    fun validateSenha(senha: String): Boolean {
        return !(senha.isEmpty() || senha.isBlank())
    }

}