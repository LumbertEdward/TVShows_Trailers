package com.example.tvshowstrails.fragmentClasses

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.PopularAdapter
import com.example.tvshowstrails.R
import com.example.tvshowstrails.dataClasses.Popular
import com.example.tvshowstrails.dataClasses.PopularAll
import com.example.tvshowstrails.dataClasses.ScrollingRecycler
import com.example.tvshowstrails.interfaceClasses.PopularInterface
import com.example.tvshowstrails.retrofitClasses.PopularRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularFragment : Fragment() {
    private val key: String = "bfb9dfa7c6ccc29b4bdee1ec785dcb7c"
    private val language = "en-US"
    private val page = 1
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private val TOTAL_PAGES: Int = 10
    private var START_PAGE = 1
    private var CURRENT_PAGE = START_PAGE
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var popularInterface: PopularInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_popular, container, false)
        progressBar = view.findViewById(R.id.progressPopular)
        progressBar.visibility = View.VISIBLE
        recyclerView = view.findViewById(R.id.recyclerPopular)
        val activity = activity as Context
        layoutManager = GridLayoutManager(activity, 3)
        popularAdapter = PopularAdapter(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = popularAdapter

        var scrollListener = object : ScrollingRecycler(layoutManager) {
            override fun loadMoreItems() {
                isLastPage = false
                CURRENT_PAGE += 1
                loadMoreData()
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }
        }
        loadFirstItem()
        recyclerView.addOnScrollListener(scrollListener)
        return view
    }

    private fun loadFirstItem() {
        popularInterface = PopularRetrofit.getMRetrofit().create(PopularInterface::class.java)
        val call = popularInterface.getPopularData(key, language, CURRENT_PAGE)
        call.enqueue(object: Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
            }

            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                progressBar.visibility = View.GONE
                getData(response.body()!!.allPopular)
            }

        })
    }

    private fun getData(allPopular: ArrayList<Popular>) {
        popularAdapter.addAllData(allPopular)
        if (CURRENT_PAGE != TOTAL_PAGES){
            popularAdapter.addFooter()
        }
        else{
            isLastPage = true
        }
    }

    private fun loadMoreData() {
        popularInterface = PopularRetrofit.getMRetrofit().create(PopularInterface::class.java)
        var call = popularInterface.getPopularData(key, language, CURRENT_PAGE)
        call.enqueue(object: Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
            }

            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                progressBar.visibility = View.GONE
                getMoreData(response.body()!!.allPopular)
            }

        })
    }

    private fun getMoreData(allPopular: ArrayList<Popular>) {
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