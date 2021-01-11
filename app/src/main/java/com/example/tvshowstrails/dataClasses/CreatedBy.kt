package com.example.tvshowstrails.dataClasses

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class CreatedBy(
        @SerializedName("id")
        @Expose
        val id: Int? = null,
        @SerializedName("credit_id")
        @Expose
        val credit_id: String? = null,
        @SerializedName("name")
        @Expose
        val name: String? = null,
        @SerializedName("gender")
        @Expose
        val gender: Int? = null,
        @SerializedName("profile_path")
        @Expose
        val profile_path: String? = null
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeValue(id)
                parcel.writeString(credit_id)
                parcel.writeString(name)
                parcel.writeValue(gender)
                parcel.writeString(profile_path)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<CreatedBy> {
                override fun createFromParcel(parcel: Parcel): CreatedBy {
                        return CreatedBy(parcel)
                }

                override fun newArray(size: Int): Array<CreatedBy?> {
                        return arrayOfNulls(size)
                }
        }
}