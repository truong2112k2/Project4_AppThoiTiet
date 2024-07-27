package com.example.da4_appthoitiet.data.weather

import android.os.Parcel
import android.os.Parcelable

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Astro::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readParcelable(Day::class.java.classLoader)!!,
        parcel.createTypedArrayList(Hour.CREATOR)!! // sử lý 1 list
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(astro, flags)
        parcel.writeString(date)
        parcel.writeInt(date_epoch)
        parcel.writeParcelable(day, flags)
        parcel.writeTypedList(hour)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Forecastday> {
        override fun createFromParcel(parcel: Parcel): Forecastday {
            return Forecastday(parcel)
        }

        override fun newArray(size: Int): Array<Forecastday?> {
            return arrayOfNulls(size)
        }
    }
}