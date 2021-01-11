package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class Popular(
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("original_name")
        var original_name: String? = null,
        @SerializedName("vote_average")
        var vote_average: Double? = null,
        @SerializedName("backdrop_path")
        var backdrop_path: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("popularity")
        var popularity: Double? = null,
        @SerializedName("poster_path")
        var poster_path: String? = null,
        @SerializedName("overview")
        var overview: String? = null,
        @SerializedName("first_air_date")
        var first_air_date: String? = null,
        @SerializedName("original_language")
        var original_language: String? = null,
        @SerializedName("genre_ids")
        var genreIds: List<Int> = ArrayList(),
        @SerializedName("vote_count")
        var voteCount: Int? = null,
        @SerializedName("origin_country")
        var origin_country: List<String> = ArrayList()
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                TODO("genreIds"),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.createStringArrayList()!!) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(name)
                parcel.writeString(original_name)
                parcel.writeValue(vote_average)
                parcel.writeString(backdrop_path)
                parcel.writeValue(id)
                parcel.writeValue(popularity)
                parcel.writeString(poster_path)
                parcel.writeString(overview)
                parcel.writeString(first_air_date)
                parcel.writeString(original_language)
                parcel.writeValue(voteCount)
                parcel.writeStringList(origin_country)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Popular> {
                override fun createFromParcel(parcel: Parcel): Popular {
                        return Popular(parcel)
                }

                override fun newArray(size: Int): Array<Popular?> {
                        return arrayOfNulls(size)
                }
        }
}
