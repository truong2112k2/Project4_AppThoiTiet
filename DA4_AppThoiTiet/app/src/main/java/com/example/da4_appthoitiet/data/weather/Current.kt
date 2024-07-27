package com.example.da4_appthoitiet.data.weather

import android.os.Parcel
import android.os.Parcelable

data class Current(
    val cloud: Int, //
    val condition: Condition, // 0
    val dewpoint_c: Double,
    val dewpoint_f: Double,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val heatindex_c: Double,
    val heatindex_f: Double,
    val humidity: Int,
    val is_day: Int,
    val last_updated: String,//0
    val last_updated_epoch: Int,
    val precip_in: Double,
    val precip_mm: Double,
    val pressure_in: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val temp_f: Double,
    val uv: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val wind_degree: Int,
    val wind_dir: String, // 0
    val wind_kph: Double,
    val wind_mph: Double,
    val windchill_c: Double,
    val windchill_f: Double,
) : Parcelable {
    constructor(parcel: Parcel) : this(
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
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
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
        parcel.writeString(last_updated)
        parcel.writeInt(last_updated_epoch)
        parcel.writeDouble(precip_in)
        parcel.writeDouble(precip_mm)
        parcel.writeDouble(pressure_in)
        parcel.writeDouble(pressure_mb)
        parcel.writeDouble(temp_c)
        parcel.writeDouble(temp_f)
        parcel.writeDouble(uv)
        parcel.writeDouble(vis_km)
        parcel.writeDouble(vis_miles)
        parcel.writeInt(wind_degree)
        parcel.writeString(wind_dir)
        parcel.writeDouble(wind_kph)
        parcel.writeDouble(wind_mph)
        parcel.writeDouble(windchill_c)
        parcel.writeDouble(windchill_f)
    }

    companion object CREATOR : Parcelable.Creator<Current> {
        override fun createFromParcel(parcel: Parcel): Current {
            return Current(parcel)
        }

        override fun newArray(size: Int): Array<Current?> {
            return arrayOfNulls(size)
        }
    }
}