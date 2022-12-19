package com.barqueroingresso.features.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("nome")
    val email : String?,
    @SerializedName("senha")
    val senha: String?
)
