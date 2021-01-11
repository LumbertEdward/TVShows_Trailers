package com.example.tvshowstrails.interfaceClasses

import com.example.tvshowstrails.dataClasses.GenresAll
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresInterface {
    @GET("genre/tv/list")
    fun getGenreData(
            @Query("api_key") string: String,
            @Query("language") language: String
    ): Call<GenresAll>
}