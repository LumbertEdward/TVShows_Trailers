package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SpokenLanguage(
        @SerializedName("english_name")
        @Expose
        val english_name: String? = null,
        @SerializedName("name")
        @Expose
        val name: String? = null,
        @SerializedName("iso_639_1")
        @Expose
        val iso_639_1: String? = null
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(english_name)
                parcel.writeString(name)
                parcel.writeString(iso_639_1)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<SpokenLanguage> {
                override fun createFromParcel(parcel: Parcel): SpokenLanguage {
                        return SpokenLanguage(parcel)
                }

                override fun newArray(size: Int): Array<SpokenLanguage?> {
                        return arrayOfNulls(size)
                }
        }
}