package com.example.tvshowstrails.interfaceClasses

import com.example.tvshowstrails.dataClasses.EpisodesAll
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesInterface {
    @GET("tv/{tv_id}/season/{season_number}")
    fun getEpisodesData(
            @Path("tv_id") id: Int,
            @Path("season_number") season: Int,
            @Query("api_key") key: String,
            @Query("language") language: String
    ): Call<EpisodesAll>
}