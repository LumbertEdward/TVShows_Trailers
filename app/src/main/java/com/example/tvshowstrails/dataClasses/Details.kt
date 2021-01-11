package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Details(
        @SerializedName("backdrop_path")
        @Expose
        val backdropPath: String? = null,
        @SerializedName("created_by")
        @Expose
        val createdBy: ArrayList<CreatedBy>  = ArrayList(),
        @SerializedName("episode_run_time")
        @Expose
        val episodeRunTime: ArrayList<Int>  = ArrayList(),
        @SerializedName("first_air_date")
        @Expose
        val firstAirDate: String? = null,
        @SerializedName("genres")
        @Expose
        val genres: ArrayList<Genres>  = ArrayList(),
        @SerializedName("homepage")
        @Expose
        val homepage: String? = null,
        @SerializedName("id")
        @Expose
        val id: Int? = null,
        @SerializedName("in_production")
        @Expose
        val inProduction: Boolean = false,
        @SerializedName("languages")
        @Expose
        val languages: ArrayList<String>  = ArrayList(),
        @SerializedName("last_air_date")
        @Expose
        val lastAirDate: String? = null,
        @SerializedName("last_episode_to_air")
        @Expose
        val lastEpisodeToAir: LastEpisodeToAir? = null,
        @SerializedName("name")
        @Expose
        val name: String? = null,
        @SerializedName("next_episode_to_air")
        @Expose
        val nextEpisodeToAir: NextEpisodeToAir? = null,
        @SerializedName("networks")
        @Expose
        val networks: ArrayList<Network>  = ArrayList(),
        @SerializedName("number_of_episodes")
        @Expose
        val numberOfEpisodes: Int? = null,
        @SerializedName("number_of_seasons")
        @Expose
        val numberOfSeasons: Int? = null,
        @SerializedName("origin_country")
        @Expose
        val originCountry: ArrayList<String>  = ArrayList(),
        @SerializedName("original_language")
        @Expose
        val originalLanguage: String? = null,
        @SerializedName("original_name")
        @Expose
        val originalName: String? = null,
        @SerializedName("overview")
        @Expose
        val overview: String? = null,
        @SerializedName("popularity")
        @Expose
        val popularity: Double? = null,
        @SerializedName("poster_path")
        @Expose
        val posterPath: String? = null,
        @SerializedName("production_companies")
        @Expose
        val productionCompanies: ArrayList<ProductionCompany>  = ArrayList(),
        @SerializedName("production_countries")
        @Expose
        val productionCountries: ArrayList<ProductionCountry>  = ArrayList(),
        @SerializedName("seasons")
        @Expose
        val seasons: ArrayList<Season>  = ArrayList(),
        @SerializedName("spoken_languages")
        @Expose
        val spokenLanguages: ArrayList<SpokenLanguage>  = ArrayList(),
        @SerializedName("status")
        @Expose
        val status: String? = null,
        @SerializedName("tagline")
        @Expose
        val tagline: String? = null,
        @SerializedName("type")
        @Expose
        val type: String? = null,
        @SerializedName("vote_average")
        @Expose
        val voteAverage: Double? = null,
        @SerializedName("vote_count")
        @Expose
        val voteCount: Int? = null
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                TODO("createdBy"),
                TODO("episodeRunTime"),
                parcel.readString(),
                TODO("genres"),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readByte() != 0.toByte(),
                TODO("languages"),
                parcel.readString(),
                parcel.readParcelable(LastEpisodeToAir::class.java.classLoader),
                parcel.readString(),
                parcel.readParcelable(NextEpisodeToAir::class.java.classLoader),
                TODO("networks"),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                TODO("originCountry"),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Float::class.java.classLoader) as? Double,
                parcel.readString(),
                TODO("productionCompanies"),
                TODO("productionCountries"),
                TODO("seasons"),
                TODO("spokenLanguages"),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Float::class.java.classLoader) as? Double,
                parcel.readValue(Int::class.java.classLoader) as? Int) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(backdropPath)
                parcel.writeString(firstAirDate)
                parcel.writeString(homepage)
                parcel.writeValue(id)
                parcel.writeByte(if (inProduction) 1 else 0)
                parcel.writeString(lastAirDate)
                parcel.writeParcelable(lastEpisodeToAir, flags)
                parcel.writeString(name)
                parcel.writeParcelable(nextEpisodeToAir, flags)
                parcel.writeValue(numberOfEpisodes)
                parcel.writeValue(numberOfSeasons)
                parcel.writeString(originalLanguage)
                parcel.writeString(originalName)
                parcel.writeString(overview)
                parcel.writeValue(popularity)
                parcel.writeString(posterPath)
                parcel.writeString(status)
                parcel.writeString(tagline)
                parcel.writeString(type)
                parcel.writeValue(voteAverage)
                parcel.writeValue(voteCount)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Details> {
                override fun createFromParcel(parcel: Parcel): Details {
                        return Details(parcel)
                }

                override fun newArray(size: Int): Array<Details?> {
                        return arrayOfNulls(size)
                }
        }
}