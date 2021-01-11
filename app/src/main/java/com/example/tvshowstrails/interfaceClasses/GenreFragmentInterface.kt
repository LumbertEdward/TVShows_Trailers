package com.example.tvshowstrails.interfaceClasses

import com.example.tvshowstrails.dataClasses.PopularAll
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreFragmentInterface {
    @GET("tv/popular")
    fun getPopularData(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<PopularAll>
}