package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PopularAll(
    @SerializedName("results")
    val allPopular: ArrayList<Popular> = ArrayList(),
    @SerializedName("total_pages")
    val total_pages: Int? = null,
    @SerializedName("total_results")
    val total_results: Int? = null

): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.createTypedArrayList(Popular)!!,
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(allPopular)
        parcel.writeValue(total_pages)
        parcel.writeValue(total_results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PopularAll> {
        override fun createFromParcel(parcel: Parcel): PopularAll {
            return PopularAll(parcel)
        }

        override fun newArray(size: Int): Array<PopularAll?> {
            return arrayOfNulls(size)
        }
    }
}