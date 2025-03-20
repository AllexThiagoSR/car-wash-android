package com.app.carwash.domain.wash

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface WashApiService {
    @GET("washes")
    fun getWashes(@Header("Authorization") token: String): Call<List<Wash>>
}