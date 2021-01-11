package com.example.tvshowstrails.retrofitClasses

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PopularRetrofit {
    companion object{
        private const val baseUrl: String = "https://api.themoviedb.org/3/"
        fun getMRetrofit(): Retrofit{
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build()
        }
    }
}