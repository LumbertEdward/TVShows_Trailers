package com.example.tvshowstrails.fragmentClasses

import android.content.Context
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.PopularAdapter
import com.example.tvshowstrails.R
import com.example.tvshowstrails.adapterClasses.GenreAdapter
import com.example.tvshowstrails.dataClasses.Genres
import com.example.tvshowstrails.dataClasses.GenresAll
import com.example.tvshowstrails.interfaceClasses.GenresInterface
import com.example.tvshowstrails.retrofitClasses.GenresRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenresFragment : Fragment() {
    private val key: String = "bfb9dfa7c6ccc29b4bdee1ec785dcb7c"
    private val language = "en-US"
    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var progressBar: ProgressBar
    private lateinit var genresInterface: GenresInterface
    private lateinit var genreAdapter: GenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_genres, container, false)
        recyclerView = view.findViewById(R.id.recyclerGenres)
        var activity = activity as Context
        linearLayoutManager = LinearLayoutManager(activity)
        progressBar = view.findViewById(R.id.progressGenres)
        progressBar.visibility = View.VISIBLE
        getGenreData()
        return view
    }

    private fun getGenreData() {
        genresInterface = GenresRetrofit.getRetrofit().create(GenresInterface::class.java)
        var call: Call<GenresAll> = genresInterface.getGenreData(key, language)
        call.enqueue(object : Callback<GenresAll>{
            override fun onFailure(call: Call<GenresAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
            }

            override fun onResponse(call: Call<GenresAll>, response: Response<GenresAll>) {
                progressBar.visibility = View.GONE
                getData(response.body()!!.genList)
            }

        })
    }

    private fun getData(genList: ArrayList<Genres>) {
        var activity = activity as Context
        genreAdapter = GenreAdapter(activity, genList)
        recyclerView.adapter = genreAdapter
        recyclerView.layoutManager = linearLayoutManager

    }


}