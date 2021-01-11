package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Crew(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("gender")
        val gender: Int? = null,
        @SerializedName("popularity")
        val popularity: Double? = null,
        @SerializedName("job")
        val job: String? = null,
        @SerializedName("department")
        val department: String? = null,
        @SerializedName("credit_id")
        val credit_id: String? = null,
        @SerializedName("known_for_department")
        val known_for_department: String? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("original_name")
        val original_name: String? = null,
        @SerializedName("profile_path")
        val profile_path: String? = null,
        @SerializedName("adult")
        val adult: Boolean = false
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readValue(Double::class.java.classLoader) as? Double,
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readByte() != 0.toByte()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeValue(id)
                parcel.writeValue(gender)
                parcel.writeValue(popularity)
                parcel.writeString(job)
                parcel.writeString(department)
                parcel.writeString(credit_id)
                parcel.writeString(known_for_department)
                parcel.writeString(name)
                parcel.writeString(original_name)
                parcel.writeString(profile_path)
                parcel.writeByte(if (adult) 1 else 0)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Crew> {
                override fun createFromParcel(parcel: Parcel): Crew {
                        return Crew(parcel)
                }

                override fun newArray(size: Int): Array<Crew?> {
                        return arrayOfNulls(size)
                }
        }
}