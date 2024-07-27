package com.example.da4_appthoitiet.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.da4_appthoitiet.MainActivity
import com.example.da4_appthoitiet.R
import com.example.da4_appthoitiet.adapter.adpaterWeatherByHour
import com.example.da4_appthoitiet.data.weather.Hour
import com.example.da4_appthoitiet.data.weather.dataWeather
import com.example.da4_appthoitiet.databinding.ActivityScreenCurrentDetailBinding
import com.example.da4_appthoitiet.databinding.AlertdialogAnnonateBinding
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class ScreenCurrentWeatherByHour : AppCompatActivity() {
    private lateinit var binding: ActivityScreenCurrentDetailBinding
    private lateinit var listHour: MutableList<Hour>
    private lateinit var adapter: adpaterWeatherByHour
    private lateinit var getData: dataWeather
    private lateinit var animation: Animation
    private lateinit var alertDialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenCurrentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createAlertDialog()
        animation = AnimationUtils.loadAnimation(this@ScreenCurrentWeatherByHour, R.anim.animation)
        try {
            getData = intent.getParcelableExtra<dataWeather>("data")!!
            showData()

        } catch (e: Exception) {
            Log.i("data_fail", e.message.toString())
        }
        toLastScreen()



    }

    private fun createAlertDialog() {
        binding.btnQuestion.setOnClickListener {
            binding.btnQuestion.startAnimation(animation)
            val build = AlertDialog.Builder(this@ScreenCurrentWeatherByHour)
            val view = AlertdialogAnnonateBinding.inflate(layoutInflater)
            build.setView(view.root)
            alertDialog = build.create()
            alertDialog.show()
        }

    }

    private fun toLastScreen() {
        binding.btnBack.setOnClickListener {
            binding.btnBack.startAnimation(animation)
            onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showData() {
        Picasso.get().load("https:" + getData.current.condition.icon).into(binding.imgWeather)
        binding.txtTemperature.text = getData.current.temp_c.toString() + "°C" + " | " + getData.current.temp_f.toString() + "°F"
        binding.txtSpeedWind.text = getData.current.wind_kph.toString() + " km/g"
        binding.txtAirPressrure.text = getData.current.pressure_mb.toString() + " mb"
        binding.txtDew.text = getData.current.dewpoint_c.toString()
        binding.txtRain.text = getData.current.precip_mm.toString() + " mm"
        binding.txtSunUV.text = getData.current.uv.toString()
        binding.txtVision.text = getData.current.vis_km.toString() + " km/h"
        binding.txtTimeSunSet.text = getData.forecast.forecastday[0].astro.sunset
        binding.txtTimeSunRise.text = getData.forecast.forecastday[0].astro.sunrise
        binding.txtTimeMoonRise.text = getData.forecast.forecastday[0].astro.moonrise
        binding.txtTimeMoonSet.text = getData.forecast.forecastday[0].astro.moonset
        listHour = getData.forecast.forecastday[0].hour.toMutableList() // get list hour current day
        adapter = adpaterWeatherByHour(this@ScreenCurrentWeatherByHour,listHour)
        binding.recycleShowWeatherHour.layoutManager = LinearLayoutManager(this@ScreenCurrentWeatherByHour)
        binding.recycleShowWeatherHour.adapter = adapter



    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}