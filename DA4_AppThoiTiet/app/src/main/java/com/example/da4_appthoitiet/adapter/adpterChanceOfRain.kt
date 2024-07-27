package com.example.da4_appthoitiet.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.da4_appthoitiet.data.weather.Forecastday
import com.example.da4_appthoitiet.databinding.ChartItemBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class adpterChanceOfRain(val list: List<Forecastday>) :
    RecyclerView.Adapter<adpterChanceOfRain.viewHolder>() {

    inner class viewHolder(val binding: ChartItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding = ChartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.txtDate.text = getWeeksDay(list[position].date.toString())
        holder.binding.progressChaceOfRain.progress = list[position].day.daily_chance_of_rain
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NewApi")
    fun getWeeksDay(dateString: String, format: String = "yyyy-MM-dd"): String {
        val formatter = DateTimeFormatter.ofPattern(format)
        val ngay = LocalDate.parse(dateString, formatter)
        return ngay.dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale("vi"))
    }
}