package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Trailers(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("key")
        val key: String? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("site")
        val site: String? = null,
        @SerializedName("size")
        val size: Int? = null,
        @SerializedName("type")
        val type: String? = null,
        @SerializedName("iso_639_1")
        val iso_639_1: String? = null,
        @SerializedName("iso_3166_1")
        val iso_3166_1: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(key)
        parcel.writeString(name)
        parcel.writeString(site)
        parcel.writeValue(size)
        parcel.writeString(type)
        parcel.writeString(iso_639_1)
        parcel.writeString(iso_3166_1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trailers> {
        override fun createFromParcel(parcel: Parcel): Trailers {
            return Trailers(parcel)
        }

        override fun newArray(size: Int): Array<Trailers?> {
            return arrayOfNulls(size)
        }
    }
}