package com.example.fastfood_app.Data

import android.util.Log
import com.example.fastfood_app.ApiService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.11/RESTFUL_API_PHP/api/"

    val api :ApiService by lazy {
        Log.d("RetrofitClient", "Initializing Retrofit with Base URL: $BASE_URL")
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory
                    .create(GsonBuilder().setLenient().create()))
            .build()
            .also { Log.d("RetrofitClient", "Retrofit built successfully") }
            .create(ApiService::class.java)
            .also { Log.d("RetrofitClient", "ApiService created successfully") }
    }

}