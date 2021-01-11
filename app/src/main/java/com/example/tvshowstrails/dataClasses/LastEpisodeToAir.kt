package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class LastEpisodeToAir(
        @SerializedName("air_date")
        val air_date: String? = null,
        @SerializedName("episode_number")
        val episode_number: Int? = null,
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("overview")
        val overview: String? = null,
        @SerializedName("production_code")
        val production_code: String? = null,
        @SerializedName("season_number")
        val season_number: Int? = null,
        @SerializedName("still_path")
        val still_path: String? = null,
        @SerializedName("vote_average")
        val vote_average: Double? = null,
        @SerializedName("vote_count")
        val vote_count: Int? = null
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Double,
                parcel.readValue(Int::class.java.classLoader) as? Int) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(air_date)
                parcel.writeValue(episode_number)
                parcel.writeValue(id)
                parcel.writeString(name)
                parcel.writeString(overview)
                parcel.writeString(production_code)
                parcel.writeValue(season_number)
                parcel.writeString(still_path)
                parcel.writeValue(vote_average)
                parcel.writeValue(vote_count)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<LastEpisodeToAir> {
                override fun createFromParcel(parcel: Parcel): LastEpisodeToAir {
                        return LastEpisodeToAir(parcel)
                }

                override fun newArray(size: Int): Array<LastEpisodeToAir?> {
                        return arrayOfNulls(size)
                }
        }
}