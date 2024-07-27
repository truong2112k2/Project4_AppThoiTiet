package com.example.da4_appthoitiet.data.weather

import android.os.Parcel
import android.os.Parcelable

data class Day(
    val avghumidity: Int,
    val avgtemp_c: Double,
    val avgtemp_f: Double,
    val avgvis_km: Double,
    val avgvis_miles: Double,
    val condition: Condition,
    val daily_chance_of_rain: Int,
    val daily_chance_of_snow: Int,
    val daily_will_it_rain: Int,
    val daily_will_it_snow: Int,
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val maxwind_kph: Double,
    val maxwind_mph: Double,
    val mintemp_c: Double,
    val mintemp_f: Double,
    val totalprecip_in: Double,
    val totalprecip_mm: Double,
    val totalsnow_cm: Double,
    val uv: Double,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readParcelable(Condition::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
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
        parcel.readDouble()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(avghumidity)
        parcel.writeDouble(avgtemp_c)
        parcel.writeDouble(avgtemp_f)
        parcel.writeDouble(avgvis_km)
        parcel.writeDouble(avgvis_miles)
        parcel.writeParcelable(condition, flags)
        parcel.writeInt(daily_chance_of_rain)
        parcel.writeInt(daily_chance_of_snow)
        parcel.writeInt(daily_will_it_rain)
        parcel.writeInt(daily_will_it_snow)
        parcel.writeDouble(maxtemp_c)
        parcel.writeDouble(maxtemp_f)
        parcel.writeDouble(maxwind_kph)
        parcel.writeDouble(maxwind_mph)
        parcel.writeDouble(mintemp_c)
        parcel.writeDouble(mintemp_f)
        parcel.writeDouble(totalprecip_in)
        parcel.writeDouble(totalprecip_mm)
        parcel.writeDouble(totalsnow_cm)
        parcel.writeDouble(uv)
    }

    companion object CREATOR : Parcelable.Creator<Day> {
        override fun createFromParcel(parcel: Parcel): Day {
            return Day(parcel)
        }

        override fun newArray(size: Int): Array<Day?> {
            return arrayOfNulls(size)
        }
    }
}