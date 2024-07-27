package com.example.da4_appthoitiet.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.da4_appthoitiet.MainActivity
import com.example.da4_appthoitiet.R
import com.example.da4_appthoitiet.adapter.adpaterWeatherByHour
import com.example.da4_appthoitiet.data.weather.Day
import com.example.da4_appthoitiet.data.weather.Hour
import com.example.da4_appthoitiet.databinding.ActivityScreenDateWeatherByHourBinding
import com.example.da4_appthoitiet.databinding.AlertdialogAnnonateBinding
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class ScreenDateWeatherByHour : AppCompatActivity() {
    private lateinit var binding: ActivityScreenDateWeatherByHourBinding
    private lateinit var listData: List<Hour>
    private lateinit var dataDay: Day
    private lateinit var date: String
    private lateinit var adapter: adpaterWeatherByHour
    private lateinit var animation: Animation
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityScreenDateWeatherByHourBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        createAlertDialog()
        animation = AnimationUtils.loadAnimation(this@ScreenDateWeatherByHour, R.anim.animation)
        try {
            listData = intent.getParcelableArrayListExtra<Hour>("listHour")!!
            dataDay = intent.getParcelableExtra<Day>("dataDay")!!
            date = intent.getStringExtra("date")!!
            showData()
        } catch (e: Exception) {
            Log.i("data_fail", e.message.toString())
        }
        toLastScreen()


    }

    private fun toLastScreen() {
        binding.btnBack.setOnClickListener {
            binding.btnBack.startAnimation(animation)
            onBackPressed()
        }
    }

    private fun createAlertDialog() {
        binding.btnQuestion.setOnClickListener {
            binding.btnQuestion.startAnimation(animation)
            val build = AlertDialog.Builder(this@ScreenDateWeatherByHour)
            val view = AlertdialogAnnonateBinding.inflate(layoutInflater)
            build.setView(view.root)
            alertDialog = build.create()
            alertDialog.show()
        }

    }
    @SuppressLint("SetTextI18n")
    private fun showData() {
        adapter = adpaterWeatherByHour(this@ScreenDateWeatherByHour,listData)
        binding.recycleShowWeatherHour.layoutManager =
            LinearLayoutManager(this@ScreenDateWeatherByHour)
        binding.recycleShowWeatherHour.adapter = adapter
        binding.txtShowDate.text = reverseDateFormat(date)
        binding.txtShowWeeksday.text = getWeeksDay(date)
        Picasso.get().load("https:" + dataDay.condition.icon).into(binding.imgWeather)
        binding.txtTemperature.text =
            dataDay.avgtemp_c.toString() + "°C" + " | " + dataDay.avgtemp_f.toString() + "°F"
        binding.txtSpeedWind.text = dataDay.maxwind_kph.toString() + " km/h"
        binding.txtChanceOfRain.text = dataDay.daily_chance_of_rain.toString() + "%"
        binding.txtSunUV.text = dataDay.uv.toString()
        binding.txtVision.text = dataDay.avgvis_km.toString() + "/km"


    }

    @SuppressLint("NewApi")
    fun getWeeksDay(chuoiNgay: String, dinhDang: String = "yyyy-MM-dd"): String {
        val formatter = DateTimeFormatter.ofPattern(dinhDang)
        val ngay = LocalDate.parse(chuoiNgay, formatter)
        return ngay.dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale("vi"))
    }

    fun reverseDateFormat(dateString: String): String {
        val parts = dateString.split("-")
        return "${parts[2]}-${parts[1]}-${parts[0]}"
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}

