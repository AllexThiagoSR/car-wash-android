package com.app.carwash

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val requester: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("http://172.22.77.14:3001/api/")
    .build()