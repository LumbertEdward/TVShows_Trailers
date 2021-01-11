package com.example.tvshowstrails.interfaceClasses

import com.example.tvshowstrails.dataClasses.TrailersAll
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrailersInterface {
    @GET("tv/{tv_id}/videos")
    fun getTrailersData(
            @Path("tv_id") id: Int,
            @Query("api_key") key: String,
            @Query("language") language: String
    ): Call<TrailersAll>
}