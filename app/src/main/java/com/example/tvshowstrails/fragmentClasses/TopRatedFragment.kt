package com.example.tvshowstrails.fragmentClasses

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.PopularAdapter
import com.example.tvshowstrails.R
import com.example.tvshowstrails.dataClasses.Popular
import com.example.tvshowstrails.dataClasses.PopularAll
import com.example.tvshowstrails.dataClasses.ScrollingRecycler
import com.example.tvshowstrails.interfaceClasses.TopRatedInterface
import com.example.tvshowstrails.retrofitClasses.TopRatedRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TopRatedFragment : Fragment() {
    private val key: String = "bfb9dfa7c6ccc29b4bdee1ec785dcb7c"
    private val language = "en-US"
    private val page = 1
    private var TOTAL_PAGES = 10
    private var START_PAGE = 1
    private var CURRENT_PAGE = START_PAGE
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var progressBar: ProgressBar
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var topRatedInterface: TopRatedInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_top_rated, container, false)
        recyclerView = view.findViewById(R.id.recyclerTopRated)
        progressBar = view.findViewById(R.id.progressTopFrag)
        progressBar.visibility = View.VISIBLE
        var activity = activity as Context
        gridLayoutManager = GridLayoutManager(activity, 3)
        popularAdapter = PopularAdapter(activity)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = popularAdapter
        recyclerView.addOnScrollListener(object: ScrollingRecycler(gridLayoutManager){
            override fun loadMoreItems() {
                isLoading = true
                CURRENT_PAGE += 1
                loadNextShows()
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

        })
        getTopData()
        return view
    }

    private fun getTopData() {
        topRatedInterface = TopRatedRetrofit.getRetrofit().create(TopRatedInterface::class.java)
        var call: Call<PopularAll> = topRatedInterface.getShows(key, language, CURRENT_PAGE)
        call.enqueue(object : Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
            }

            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                progressBar.visibility = View.GONE
                getShowsData(response.body()!!.allPopular)
            }

        })
    }

    private fun getShowsData(allPopular: ArrayList<Popular>) {
        popularAdapter.addAllData(allPopular)
        if (CURRENT_PAGE != TOTAL_PAGES){
            popularAdapter.addFooter()
        }
        else{
            isLastPage = true
        }

    }

    private fun loadNextShows() {
        topRatedInterface = TopRatedRetrofit.getRetrofit().create(TopRatedInterface::class.java)
        var call: Call<PopularAll> = topRatedInterface.getShows(key, language, CURRENT_PAGE)
        call.enqueue(object : Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
            }

            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                progressBar.visibility = View.GONE
                getNextData(response.body()!!.allPopular)
            }

        })
    }

    private fun getNextData(allPopular: ArrayList<Popular>) {
        popularAdapter.removeFooter()
        isLoading = false
        popularAdapter.addAllData(allPopular)
        if (CURRENT_PAGE != TOTAL_PAGES){
            popularAdapter.addFooter()
        }
        else{
            isLastPage = true
        }


    }

}