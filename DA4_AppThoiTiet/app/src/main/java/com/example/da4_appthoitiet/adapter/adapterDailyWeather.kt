package com.example.da4_appthoitiet.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.da4_appthoitiet.data.weather.Forecastday
import com.example.da4_appthoitiet.databinding.RecycleItemBinding
import com.example.da4_appthoitiet.screen.ScreenDateWeatherByHour
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.ArrayList
import java.util.Locale

class adapterDailyWeather(val context: Context, val list: List<Forecastday>) :
    RecyclerView.Adapter<adapterDailyWeather.viewHolder>() {
    inner class viewHolder(val binding: RecycleItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding = RecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.txtDate.text = reverseDateFormat(list[position].date.toString())
        Picasso.get().load("https:" + list[position].day.condition.icon)
            .into(holder.binding.imgWeather)
        holder.binding.txtTemperature.text = list[position].day.avgtemp_c.toString() + "°C"
        holder.binding.txtHumidity.text = list[position].day.avghumidity.toString() + "%"
        holder.binding.txtSpeedWind.text = list[position].day.maxwind_kph.toString() + " km/h"
        holder.binding.txtWeekdays.text = getWeeksDay(list[position].date.toString())
        holder.itemView.setOnClickListener {
            val i = Intent(context, ScreenDateWeatherByHour::class.java)
            i.putParcelableArrayListExtra("listHour", ArrayList(list[position].hour))
            i.putExtra("dataDay", list[position].day)
            i.putExtra("date", list[position].date)
            context.startActivity(i)



        }


    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun reverseDateFormat(dateString: String): String {
        val parts = dateString.split("-")
        return "${parts[2]}-${parts[1]}-${parts[0]}"
    }

    @SuppressLint("NewApi")
    fun getWeeksDay(dateString: String, format: String = "yyyy-MM-dd"): String {
        val formatter = DateTimeFormatter.ofPattern(format)
        val ngay = LocalDate.parse(dateString, formatter)
        return ngay.dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale("vi"))
    }
}