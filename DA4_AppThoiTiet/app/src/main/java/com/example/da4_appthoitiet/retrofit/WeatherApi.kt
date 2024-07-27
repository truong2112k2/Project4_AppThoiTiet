package com.example.da4_appthoitiet.retrofit

import com.example.da4_appthoitiet.data.weather.dataWeather
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("v1/forecast.json")

    suspend fun getWeatherForeCast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String,
        @Query("alerts") alerts: String,
    ): dataWeather
}