package com.example.da4_appthoitiet.data.weather

import android.os.Parcel
import android.os.Parcelable

data class Hour(
    val chance_of_rain: Int, // tỉ lệ mưa
    val chance_of_snow: Int, // tỉ lệ tuyết rơi
    val cloud: Int, // mây
    val condition: Condition, // icon, text
    val dewpoint_c: Double, // sương mù
    val dewpoint_f: Double,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val heatindex_c: Double,
    val heatindex_f: Double,
    val humidity: Int, // độ ẩm
    val is_day: Int, // sáng hay ối
    val precip_in: Double,
    val precip_mm: Double,
    //Lượng mưa dự kiến trong 1 giờ, đo bằng milimét và inches.
    val pressure_in: Double,
    val pressure_mb: Double,
    //Áp suất không khí hiện tại, đo bằng mBar và inches.
    val snow_cm: Double,
    //Lượng tuyết dự kiến trong 1 giờ, đo bằng centimét.
    val temp_c: Double, // nhiệt độ
    val temp_f: Double,
    val time: String, // thời gian hiện tại
    val time_epoch: Int,
    val uv: Double,// tia UV
    val vis_km: Double,// tầm nhìn
    val vis_miles: Double,
    val will_it_rain: Int, // dự đoán có mưa trong 1 h không
    val will_it_snow: Int, //dự đoán có tuyết trong 1 h không
    val wind_degree: Int,
    val wind_dir: String, // hướng gió
    val wind_kph: Double,
    val wind_mph: Double,
    val windchill_c: Double,
    val windchill_f: Double,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readParcelable(Condition::class.java.classLoader)!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(chance_of_rain)
        parcel.writeInt(chance_of_snow)
        parcel.writeInt(cloud)
        parcel.writeParcelable(condition, flags)
        parcel.writeDouble(dewpoint_c)
        parcel.writeDouble(dewpoint_f)
        parcel.writeDouble(feelslike_c)
        parcel.writeDouble(feelslike_f)
        parcel.writeDouble(gust_kph)
        parcel.writeDouble(gust_mph)
        parcel.writeDouble(heatindex_c)
        parcel.writeDouble(heatindex_f)
        parcel.writeInt(humidity)
        parcel.writeInt(is_day)
        parcel.writeDouble(precip_in)
        parcel.writeDouble(precip_mm)
        parcel.writeDouble(pressure_in)
        parcel.writeDouble(pressure_mb)
        parcel.writeDouble(snow_cm)
        parcel.writeDouble(temp_c)
        parcel.writeDouble(temp_f)
        parcel.writeString(time)
        parcel.writeInt(time_epoch)
        parcel.writeDouble(uv)
        parcel.writeDouble(vis_km)
        parcel.writeDouble(vis_miles)
        parcel.writeInt(will_it_rain)
        parcel.writeInt(will_it_snow)
        parcel.writeInt(wind_degree)
        parcel.writeString(wind_dir)
        parcel.writeDouble(wind_kph)
        parcel.writeDouble(wind_mph)
        parcel.writeDouble(windchill_c)
        parcel.writeDouble(windchill_f)
    }

    companion object CREATOR : Parcelable.Creator<Hour> {
        override fun createFromParcel(parcel: Parcel): Hour {
            return Hour(parcel)
        }

        override fun newArray(size: Int): Array<Hour?> {
            return arrayOfNulls(size)
        }
    }
}