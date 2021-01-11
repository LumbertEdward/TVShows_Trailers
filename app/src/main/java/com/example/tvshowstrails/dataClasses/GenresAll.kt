package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class GenresAll(
        @SerializedName("genres")
        val genList: ArrayList<Genres> = ArrayList()
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.createTypedArrayList(Genres)!!
                ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeTypedList(genList)

        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<GenresAll> {
                override fun createFromParcel(parcel: Parcel): GenresAll {
                        return GenresAll(parcel)
                }

                override fun newArray(size: Int): Array<GenresAll?> {
                        return arrayOfNulls(size)
                }
        }
}