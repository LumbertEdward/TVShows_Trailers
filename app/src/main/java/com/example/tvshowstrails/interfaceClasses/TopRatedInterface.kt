package com.example.tvshowstrails.interfaceClasses

import android.telecom.Call
import com.example.tvshowstrails.dataClasses.PopularAll
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedInterface {
    @GET("tv/top_rated")
    fun getShows(
        @Query("api_key") string: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): retrofit2.Call<PopularAll>
}