package com.example.da4_appthoitiet.data.weather

import android.os.Parcel
import android.os.Parcelable

data class Astro(
    val is_moon_up: Int,
    val is_sun_up: Int,
    val moon_illumination: Int,
    val moon_phase: String,
    val moonrise: String,
    val moonset: String,
    val sunrise: String,
    val sunset: String,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(is_moon_up)
        p0.writeInt(is_sun_up)
        p0.writeInt(moon_illumination)
        p0.writeString(moon_phase)
        p0.writeString(moonrise)
        p0.writeString(moonset)
        p0.writeString(sunrise)
        p0.writeString(sunset)


    }

    companion object CREATOR : Parcelable.Creator<Astro> {
        override fun createFromParcel(parcel: Parcel): Astro {
            return Astro(parcel)
        }

        override fun newArray(size: Int): Array<Astro?> {
            return arrayOfNulls(size)
        }
    }
}