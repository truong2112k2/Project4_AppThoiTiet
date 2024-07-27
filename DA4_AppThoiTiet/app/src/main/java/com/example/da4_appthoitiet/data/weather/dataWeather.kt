package com.example.da4_appthoitiet.data.weather

import android.os.Parcel
import android.os.Parcelable

data class dataWeather(
    val current: Current,
    val forecast: Forecast,
    val location: Location,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Current::class.java.classLoader)!!,
        parcel.readParcelable(Forecast::class.java.classLoader)!!,
        parcel.readParcelable(Location::class.java.classLoader)!!
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeParcelable(current, p1)
        p0.writeParcelable(forecast, p1)
        p0.writeParcelable(location, p1)
    }

    companion object CREATOR : Parcelable.Creator<dataWeather> {
        override fun createFromParcel(parcel: Parcel): dataWeather {
            return dataWeather(parcel)
        }

        override fun newArray(size: Int): Array<dataWeather?> {
            return arrayOfNulls(size)
        }
    }
}