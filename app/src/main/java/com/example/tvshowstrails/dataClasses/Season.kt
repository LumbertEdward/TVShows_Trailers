package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Season(
        @SerializedName("air_date")
        val air_date: String? = null,
        @SerializedName("episode_count")
        val episode_count: Int? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("overview")
        val overview: String? = null,
        @SerializedName("poster_path")
        val poster_path: String? = null,
        @SerializedName("season_number")
        val season_number: Int? = null
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(air_date)
                parcel.writeValue(episode_count)
                parcel.writeValue(id)
                parcel.writeString(name)
                parcel.writeString(overview)
                parcel.writeString(poster_path)
                parcel.writeValue(season_number)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Season> {
                override fun createFromParcel(parcel: Parcel): Season {
                        return Season(parcel)
                }

                override fun newArray(size: Int): Array<Season?> {
                        return arrayOfNulls(size)
                }
        }
}