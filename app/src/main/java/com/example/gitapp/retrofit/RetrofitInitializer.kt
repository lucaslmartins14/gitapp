package com.example.gitapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    val retrofit = Retrofit.Builder().baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun grService(): GrService? = retrofit.create(GrService::class.java)

}