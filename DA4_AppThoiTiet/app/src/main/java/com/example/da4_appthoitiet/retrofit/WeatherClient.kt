package com.example.da4_appthoitiet.retrofit

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherClient {
    val baseUrl = "https://api.weatherapi.com/"
    val intansce: WeatherApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(WeatherApi::class.java)
    }
}