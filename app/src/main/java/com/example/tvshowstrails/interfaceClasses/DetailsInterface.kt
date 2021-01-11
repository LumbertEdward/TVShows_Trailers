package com.example.tvshowstrails.interfaceClasses

import com.example.tvshowstrails.dataClasses.Details
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsInterface {
    @GET("tv/{tv_id}")
    fun getDetailsData(
            @Path("tv_id") tv_id: Int,
            @Query("api_key") string: String,
            @Query("language") language: String
    ): Call<Details>
}