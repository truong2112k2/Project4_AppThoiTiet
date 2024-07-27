package com.example.da4_appthoitiet.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.da4_appthoitiet.R
import com.example.da4_appthoitiet.data.weather.dataWeather
import com.example.da4_appthoitiet.retrofit.WeatherClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class viewmodel(): ViewModel() {
   private val apikey = "0d85364f2c61440eab083125241507"
   private val days = 10
    private val aqi = "no"
    private val alerts = "no"
   val retrofit = WeatherClient.intansce
    private val _weatherData = MutableLiveData<dataWeather>()
    val weatherData : LiveData<dataWeather> get() = _weatherData


    fun getDataFromApi(location: String){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = retrofit.getWeatherForeCast(
                    apikey,
                    location,
                    days,
                    aqi,
                    alerts)
                withContext(Dispatchers.Main){
                    _weatherData.value = data
                }
            }catch (e : Exception){
                Log.i("retrofit_error", e.message.toString())
            }

            }

    }



}