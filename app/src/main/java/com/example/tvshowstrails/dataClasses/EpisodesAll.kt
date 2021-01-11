package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class EpisodesAll(
        @SerializedName("air_date")
        val air_date: String? = null,
        @SerializedName("_id")
        val _id: String? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("overview")
        val overview: String? = null,
        @SerializedName("poster_path")
        val poster_path: String? = null,
        @SerializedName("season_number")
        val season_number: Int? = null,
        @SerializedName("episodes")
        val episodes: ArrayList<Episodes> = ArrayList()
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                TODO("episodes")) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(air_date)
                parcel.writeString(_id)
                parcel.writeValue(id)
                parcel.writeString(name)
                parcel.writeString(overview)
                parcel.writeString(poster_path)
                parcel.writeValue(season_number)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<EpisodesAll> {
                override fun createFromParcel(parcel: Parcel): EpisodesAll {
                        return EpisodesAll(parcel)
                }

                override fun newArray(size: Int): Array<EpisodesAll?> {
                        return arrayOfNulls(size)
                }
        }
}