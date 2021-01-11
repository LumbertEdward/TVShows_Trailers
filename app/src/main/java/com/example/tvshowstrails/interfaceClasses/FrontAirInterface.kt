package com.example.tvshowstrails.interfaceClasses

import com.example.tvshowstrails.dataClasses.PopularAll
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FrontAirInterface {
    @GET("tv/on_the_air")
    fun getShows(
        @Query("api_key") string: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<PopularAll>
}