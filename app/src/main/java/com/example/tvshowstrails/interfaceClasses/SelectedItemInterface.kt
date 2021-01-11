package com.example.tvshowstrails.interfaceClasses

import com.example.tvshowstrails.dataClasses.*

interface SelectedItemInterface {
    fun passId(popular: Popular?): Unit
    fun onBackPressed()
    fun getSeasonEpisodes(season: Season?)
    fun getEpisodeData(episodes: Episodes?)
    fun passTrailer(trailers: Trailers?)
    fun passThumb(popular: Popular?)
    fun getAir()
    fun getTop()
    fun getToday()
    fun getGenreDetailsData(genres: Genres?)
}