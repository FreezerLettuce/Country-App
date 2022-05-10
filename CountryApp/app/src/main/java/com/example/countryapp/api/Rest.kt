package com.example.countryapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    val apiService by lazy {
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}