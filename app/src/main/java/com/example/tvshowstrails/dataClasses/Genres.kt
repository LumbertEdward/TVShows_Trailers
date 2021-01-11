package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Genres(
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("name")
        val name: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Genres> {
        override fun createFromParcel(parcel: Parcel): Genres {
            return Genres(parcel)
        }

        override fun newArray(size: Int): Array<Genres?> {
            return arrayOfNulls(size)
        }
    }
}