package com.example.da4_appthoitiet.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.da4_appthoitiet.data.weather.Hour
import com.example.da4_appthoitiet.databinding.RecycleItemWeatherbyhourBinding
import com.example.da4_appthoitiet.screen.ScreenHourWeatherDetail
import com.squareup.picasso.Picasso
import kotlin.random.Random

class adpaterWeatherByHour(val context: Context,val list: List<Hour>) :
    RecyclerView.Adapter<adpaterWeatherByHour.viewHolder>() {
    inner class viewHolder(val binding: RecycleItemWeatherbyhourBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding = RecycleItemWeatherbyhourBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return viewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.txtTempProgress.progress = list[position].temp_c.toInt()
        holder.binding.txtTempProgress.suffix = "°C"

        val a = Random.nextInt(0, 5)
        if (a == 1) {
            holder.binding.txtTempProgress.reachedBarColor = Color.parseColor("#FF1113")
        } else if (a == 2) {
            holder.binding.txtTempProgress.reachedBarColor = Color.parseColor("#FFFF00")
        } else if (a == 3) {
            holder.binding.txtTempProgress.reachedBarColor = Color.parseColor("#00FFFF")
        } else if (a == 4) {
            holder.binding.txtTempProgress.reachedBarColor = Color.parseColor("#00FF00")
        } else if (a == 5) {
            holder.binding.txtTempProgress.reachedBarColor = Color.parseColor("#FF00FF")
        } else {
            holder.binding.txtTempProgress.reachedBarColor = Color.parseColor("#FFFFFF")
        }
        Picasso.get().load("https:" + list[position].condition.icon)
            .into(holder.binding.txtShowIcon)
        if (getTimeFromDate(list[position].time.toString()).toInt() < 12) {
            holder.binding.txtShowTime.text =
                getTimeFromDate(list[position].time.toString()) + " AM"
        } else if (getTimeFromDate(list[position].time.toString()).toInt() > 12) {
            holder.binding.txtShowTime.text =
                getTimeFromDate(list[position].time.toString()) + " PM"
        }
         holder.itemView.setOnClickListener {
                val i = Intent(context, ScreenHourWeatherDetail :: class.java)
             i.putExtra("hour", list[position])
             context.startActivity(i)
         }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun getTimeFromDate(time: String): String {
        return time.split(" ")[1].substringBefore(':')
    }

}