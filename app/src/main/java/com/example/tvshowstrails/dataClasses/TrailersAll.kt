package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class TrailersAll(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("results")
        val results: ArrayList<Trailers> = ArrayList()
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            TODO("results")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrailersAll> {
        override fun createFromParcel(parcel: Parcel): TrailersAll {
            return TrailersAll(parcel)
        }

        override fun newArray(size: Int): Array<TrailersAll?> {
            return arrayOfNulls(size)
        }
    }
}