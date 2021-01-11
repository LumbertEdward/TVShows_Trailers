package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Network(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("origin_country")
        val origin_country: String? = null,
        @SerializedName("logo_path")
        val logo_path: String? = null
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeValue(id)
                parcel.writeString(name)
                parcel.writeString(origin_country)
                parcel.writeString(logo_path)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Network> {
                override fun createFromParcel(parcel: Parcel): Network {
                        return Network(parcel)
                }

                override fun newArray(size: Int): Array<Network?> {
                        return arrayOfNulls(size)
                }
        }
}