package com.example.gitapp.retrofit

import com.example.gitapp.model.entity.GR
import com.example.gitapp.model.entity.Object
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GrService {


    @GET("search/repositories")
    fun getGrs(@Query("q") q: String, @Query("sort") sort: String, @Query("page") page: Int): Call<Object>
}