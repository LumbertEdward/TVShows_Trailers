package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductionCountry(
        @SerializedName("name")
        @Expose
        val name: String? = null,
        @SerializedName("iso_3166_1")
        @Expose
        val iso_3166_1: String? = null
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(name)
                parcel.writeString(iso_3166_1)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<ProductionCountry> {
                override fun createFromParcel(parcel: Parcel): ProductionCountry {
                        return ProductionCountry(parcel)
                }

                override fun newArray(size: Int): Array<ProductionCountry?> {
                        return arrayOfNulls(size)
                }
        }
}