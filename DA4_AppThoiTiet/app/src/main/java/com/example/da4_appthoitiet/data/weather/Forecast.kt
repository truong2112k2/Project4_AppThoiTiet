package com.example.da4_appthoitiet.data.weather

import android.os.Parcel
import android.os.Parcelable


data class Forecast(
    val forecastday: List<Forecastday>,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(Forecastday.CREATOR)!!
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeTypedList(forecastday)
    }

    companion object CREATOR : Parcelable.Creator<Forecast> {
        override fun createFromParcel(parcel: Parcel): Forecast {
            return Forecast(parcel)
        }

        override fun newArray(size: Int): Array<Forecast?> {
            return arrayOfNulls(size)
        }
    }
}