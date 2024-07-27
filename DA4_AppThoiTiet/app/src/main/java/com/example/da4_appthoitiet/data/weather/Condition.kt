package com.example.da4_appthoitiet.data.weather

import android.os.Parcel
import android.os.Parcelable

data class Condition(
    val code: Int,
    val icon: String,
    val text: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeInt(code)
        p0.writeString(icon)
        p0.writeString(text)
    }

    companion object CREATOR : Parcelable.Creator<Condition> {
        override fun createFromParcel(parcel: Parcel): Condition {
            return Condition(parcel)
        }

        override fun newArray(size: Int): Array<Condition?> {
            return arrayOfNulls(size)
        }
    }
}