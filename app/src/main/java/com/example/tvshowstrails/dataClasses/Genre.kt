package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Genre(
        @SerializedName("id")
        @Expose
        val id: Int? = null,
        @SerializedName("name")
        @Expose
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

        companion object CREATOR : Parcelable.Creator<Genre> {
                override fun createFromParcel(parcel: Parcel): Genre {
                        return Genre(parcel)
                }

                override fun newArray(size: Int): Array<Genre?> {
                        return arrayOfNulls(size)
                }
        }
}