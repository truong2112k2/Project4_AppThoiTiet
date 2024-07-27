package com.example.da4_appthoitiet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.da4_appthoitiet.adapter.adpterChanceOfRain
import com.example.da4_appthoitiet.adapter.adapterDailyWeather
import com.example.da4_appthoitiet.adapter.adpaterSpinner
import com.example.da4_appthoitiet.data.weather.Forecastday
import com.example.da4_appthoitiet.data.weather.dataWeather
import com.example.da4_appthoitiet.databinding.ActivityMainBinding
import com.example.da4_appthoitiet.screen.ScreenCurrentWeatherByHour
import com.example.da4_appthoitiet.viewmodel.viewmodel
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listDataSpinner: Array<String>
    private lateinit var listDataWeather: List<Forecastday>
    private lateinit var adapterRecycleviewDailyWeather: adapterDailyWeather
    private lateinit var adapterRecycleviewDailyChanceOfRain: adpterChanceOfRain
    private lateinit var countDownTimer: CountDownTimer
    private var countryName = "null"
    val viewModel: viewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        createSpinner()
        loaddingData()


    }

    private fun loaddingData() {
        Glide.with(this@MainActivity)
            .asGif()
            .load(R.drawable.gif_earth)
            .into(binding.imgGif)

        val time= object : CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                binding.allview.visibility = View.VISIBLE
                binding.imgGif.visibility = View.GONE

            }
        }
            .start()
    }


    private fun CurrentWeatherByHour(returnData: dataWeather) {
        binding.viewCurrentWeatherByHour.setOnClickListener {
            toCurrentWeatherByHour(returnData)
        }
    }

    private fun toCurrentWeatherByHour(returnData: dataWeather) {
        val i = Intent(this@MainActivity, ScreenCurrentWeatherByHour::class.java)
        i.putExtra("data", returnData)

        startActivity(i)
    }

    private fun createSpinner() {
        listDataSpinner = resources.getStringArray(R.array.VietNamList)
        binding.mySpinner.adapter = adpaterSpinner(this@MainActivity, listDataSpinner)
        binding.mySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                viewModel.getDataFromApi(listDataSpinner[position])
                interactWithData()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun interactWithData() {
        viewModel.weatherData.observe(this@MainActivity, Observer { returnData ->
            Picasso.get().load("https:" + returnData.current.condition.icon)
                .into(binding.imgCurrentWeather)
            binding.txtTemperature.text =
                returnData.current.temp_c.toString() + "°C" + " | " + returnData.current.temp_f.toString() + "°F" // temparature C, F
            binding.txtLocation.text =
                returnData.location.name + " - " + returnData.location.country // city and country
            binding.txtSpeedWind.text = returnData.current.wind_kph.toString() + " km/h"
            binding.txtLocationTime.text = reverseDateFormatWithTime(returnData.location.localtime)
            binding.txtHumidity.text =
                returnData.current.humidity.toString() + "%"        // Humidity
            binding.txtSunUV.text = returnData.current.uv.toInt().toString()
            // show data days on recycleview daily weather
            listDataWeather = returnData.forecast.forecastday
            adapterRecycleviewDailyWeather = adapterDailyWeather(this@MainActivity, listDataWeather)
            binding.recycle10daynext.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.recycle10daynext.adapter = adapterRecycleviewDailyWeather
            // show data days on recycleview daily chance of rain

            adapterRecycleviewDailyChanceOfRain = adpterChanceOfRain(listDataWeather)
            binding.recycleChanceOfRain.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.recycleChanceOfRain.adapter = adapterRecycleviewDailyChanceOfRain



            CurrentWeatherByHour(returnData) // weather by hour
        })
    }


    fun reverseDateFormatWithTime(dateTimeString: String): String {
        val parts = dateTimeString.split(" ")
        val dateParts = parts[0].split("-")
        return "${dateParts[2]}-${dateParts[1]}-${dateParts[0]} ${parts[1]}"
    }


}