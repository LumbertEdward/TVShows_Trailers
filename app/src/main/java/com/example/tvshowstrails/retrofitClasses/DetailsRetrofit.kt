package com.example.tvshowstrails.retrofitClasses

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsRetrofit {
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