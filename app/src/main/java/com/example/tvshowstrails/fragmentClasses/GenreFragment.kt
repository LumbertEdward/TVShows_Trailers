package com.example.tvshowstrails.fragmentClasses

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.PopularAdapter
import com.example.tvshowstrails.R
import com.example.tvshowstrails.dataClasses.*
import com.example.tvshowstrails.interfaceClasses.GenreFragmentInterface
import com.example.tvshowstrails.retrofitClasses.GenreFragmentRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreFragment : Fragment() {
    private val language = "en-US"
    private val page = 1
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private val TOTAL_PAGES: Int = 10
    private var START_PAGE = 1
    private var CURRENT_PAGE = START_PAGE
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var genreFragmentInterface: GenreFragmentInterface
    private lateinit var errorWarm: TextView
    private lateinit var relAll: RelativeLayout
    private var list: ArrayList<Popular> = ArrayList()
    private var list1: ArrayList<Popular> = ArrayList()
    private var bundle: Bundle? = null
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = this.arguments
       if (bundle != null){
           id = bundle!!.getInt("GENREID")
       }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_genre, container, false)
        progressBar = view.findViewById(R.id.progressGen)
        progressBar.visibility= View.VISIBLE
        recyclerView = view.findViewById(R.id.recyclerGen)
        recyclerView.setHasFixedSize(true)
        errorWarm = view.findViewById(R.id.errorMes)
        relAll = view.findViewById(R.id.allGen)
        relAll.visibility = View.GONE
        errorWarm.visibility = View.VISIBLE
        var activity = activity as Context
        gridLayoutManager = GridLayoutManager(activity, 3)
        popularAdapter = PopularAdapter(activity)
        recyclerView.adapter = popularAdapter
        recyclerView.layoutManager = gridLayoutManager
        getData()
        return view
    }

   private fun getData() {
        recyclerView.addOnScrollListener(object : ScrollingRecycler(gridLayoutManager){
            override fun loadMoreItems() {
                isLoading = true
                CURRENT_PAGE += 1
                loadMoreGenreItems()
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

        })
        loadFirstItem()
    }

    private fun loadFirstItem() {
        genreFragmentInterface = GenreFragmentRetrofit.getMRetrofit().create(GenreFragmentInterface::class.java)
        var call: Call<PopularAll> = genreFragmentInterface.getPopularData(Constants.key, language, CURRENT_PAGE)
        call.enqueue(object : Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                relAll.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                Toast.makeText(activity, t.toString(), Toast.LENGTH_LONG).show()
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                progressBar.visibility = View.GONE
                relAll.visibility = View.VISIBLE
                getFirstData(response.body()!!.allPopular)
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getFirstData(allPopular: ArrayList<Popular>) {
        for (i in allPopular.indices){
            for (j in allPopular[i].genreIds.indices){
                if (allPopular[i].genreIds[j] == id){
                    var popular: Popular = Popular()
                    popular.name = allPopular[i].name
                    popular.genreIds = allPopular[i].genreIds
                    popular.backdrop_path = allPopular[i].backdrop_path
                    popular.first_air_date = allPopular[i].first_air_date
                    popular.origin_country = allPopular[i].origin_country
                    popular.id = allPopular[i].id
                    popular.original_language = allPopular[i].original_language
                    popular.original_name = allPopular[i].original_name
                    popular.overview = allPopular[i].overview
                    popular.popularity = allPopular[i].popularity
                    popular.poster_path = allPopular[i].poster_path
                    popular.voteCount = allPopular[i].voteCount
                    popular.vote_average = allPopular[i].vote_average

                    list.add(popular)
                    list.removeIf { popular.vote_average.toString().isNullOrEmpty() || popular.id.toString().isNullOrEmpty() }

                    if (list.isNotEmpty()){
                        errorWarm.visibility = View.GONE
                        popularAdapter.addAllData(list)
                        if (CURRENT_PAGE != TOTAL_PAGES) popularAdapter.addFooter()
                        else isLastPage = true
                    }
                    else{
                        errorWarm.visibility = View.VISIBLE
                    }

                }
            }
        }

    }

    private fun loadMoreGenreItems() {
        genreFragmentInterface = GenreFragmentRetrofit.getMRetrofit().create(GenreFragmentInterface::class.java)
        var call: Call<PopularAll> = genreFragmentInterface.getPopularData(Constants.key, language, CURRENT_PAGE)
        call.enqueue(object : Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
                relAll.visibility = View.GONE
                Toast.makeText(activity, t.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                progressBar.visibility = View.GONE
                relAll.visibility = View.VISIBLE
                getNextData(response.body()!!.allPopular)
            }

        })

    }

    private fun getNextData(allPopular: ArrayList<Popular>) {
        for (i in allPopular.indices){
            for (j in allPopular[i].genreIds.indices){
                if (allPopular[i].genreIds[j] == id){
                    var popular: Popular = Popular()
                    popular.name = allPopular[i].name
                    popular.genreIds = allPopular[i].genreIds
                    popular.backdrop_path = allPopular[i].backdrop_path
                    popular.first_air_date = allPopular[i].first_air_date
                    popular.origin_country = allPopular[i].origin_country
                    popular.id = allPopular[i].id
                    popular.original_language = allPopular[i].original_language
                    popular.original_name = allPopular[i].original_name
                    popular.overview = allPopular[i].overview
                    popular.popularity = allPopular[i].popularity
                    popular.poster_path = allPopular[i].poster_path
                    popular.voteCount = allPopular[i].voteCount
                    popular.vote_average = allPopular[i].vote_average
                    list1.add(popular)

                    if (list1.isNotEmpty()){
                        errorWarm.visibility = View.GONE
                        popularAdapter.removeFooter()
                        isLoading = false
                        popularAdapter.addAllData(list1)
                        if (CURRENT_PAGE != TOTAL_PAGES) popularAdapter.addFooter()
                        else isLastPage = true
                    }
                    else{
                        errorWarm.visibility = View.VISIBLE
                    }

                }
            }
        }


    }

}