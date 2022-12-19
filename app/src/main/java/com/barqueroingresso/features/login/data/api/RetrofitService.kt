package com.barqueroingresso.features.login.data.api

import com.barqueroingresso.features.login.data.model.LoginRequest
import com.barqueroingresso.features.login.data.model.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {

    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body LoginRequest: LoginRequest): Call<LoginResponse>

    companion object {

        private val retrofitService: RetrofitService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://quero-ingresso-server-backup.onrender.com/api/produtor/")
                .client(OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)

        }

        fun getInstance(): RetrofitService {
            return retrofitService
        }
    }

}