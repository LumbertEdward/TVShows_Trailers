package com.example.tvshowstrails.retrofitClasses

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FrontAirRet {
    companion object{
        private const val url: String = "https://api.themoviedb.org/3/"
        fun getRetrofit(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}