package com.example.tvshowstrails.fragmentClasses

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.R
import com.example.tvshowstrails.adapterClasses.SearchAdapter
import com.example.tvshowstrails.dataClasses.Popular
import com.example.tvshowstrails.dataClasses.PopularAll
import com.example.tvshowstrails.dataClasses.ScrollingRecycler
import com.example.tvshowstrails.interfaceClasses.SearchInterface
import com.example.tvshowstrails.retrofitClasses.SearchRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private val key: String = "bfb9dfa7c6ccc29b4bdee1ec785dcb7c"
    private val language = "en-US"
    private val page = 1
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private val TOTAL_PAGES: Int = 10
    private var START_PAGE = 0
    private var CURRENT_PAGE = START_PAGE
    private lateinit var recyclerView: RecyclerView
    private lateinit var back: ImageView
    private lateinit var cancel: ImageView
    private lateinit var editText: EditText
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchInterface: SearchInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = view.findViewById(R.id.recyclerSearch)
        back = view.findViewById(R.id.backSearch)
        cancel = view.findViewById(R.id.cancel)
        editText = view.findViewById(R.id.searchBox)
        var activity = activity as Context
        linearLayoutManager = LinearLayoutManager(activity)
        searchAdapter = SearchAdapter(activity)
        recyclerView.adapter = searchAdapter
        recyclerView.layoutManager = linearLayoutManager
        getData()
        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchAdapter.filter.filter(s)
            }

        })
        return view
    }

    private fun getData() {
        recyclerView.addOnScrollListener(object : ScrollingRecycler(linearLayoutManager){
            override fun loadMoreItems() {
                isLoading = true
                CURRENT_PAGE += 1
                loadMorePopularItems()

            }

            override fun isLoading(): Boolean {
               return isLoading
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }
        })
        getFirstItems()

    }

    private fun getFirstItems() {
        searchInterface = SearchRetrofit.getMRetrofit().create(SearchInterface::class.java)
        val call: Call<PopularAll> = searchInterface.getPopularData(key, language, page)
        call.enqueue(object : Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                Toast.makeText(activity, t.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                getResponse(response.body()!!.allPopular)
            }

        })
    }

    private fun getResponse(allPopular: ArrayList<Popular>) {
        searchAdapter.addAll(allPopular)
        if (CURRENT_PAGE != TOTAL_PAGES) searchAdapter.addFooter()
        else isLastPage = true

    }

    private fun loadMorePopularItems() {
        searchInterface = SearchRetrofit.getMRetrofit().create(SearchInterface::class.java)
        val call: Call<PopularAll> = searchInterface.getPopularData(key, language, page)
        call.enqueue(object : Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                Toast.makeText(activity, t.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                getNextResponse(response.body()!!.allPopular)
            }

        })
    }

    private fun getNextResponse(allPopular: ArrayList<Popular>) {
        searchAdapter.removeFooter()
        isLoading = false
        searchAdapter.addAll(allPopular)
        if (CURRENT_PAGE != TOTAL_PAGES) searchAdapter.addFooter()
        else isLastPage = true
    }

}