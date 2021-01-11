package com.example.tvshowstrails.fragmentClasses

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.R
import com.example.tvshowstrails.adapterClasses.FrontItemsAdapter
import com.example.tvshowstrails.adapterClasses.PopHomeAdapter
import com.example.tvshowstrails.dataClasses.Constants
import com.example.tvshowstrails.dataClasses.Popular
import com.example.tvshowstrails.dataClasses.PopularAll
import com.example.tvshowstrails.interfaceClasses.*
import com.example.tvshowstrails.retrofitClasses.FrontAirRet
import com.example.tvshowstrails.retrofitClasses.FrontPopRet
import com.example.tvshowstrails.retrofitClasses.FrontTodayRet
import com.example.tvshowstrails.retrofitClasses.FrontTopRet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private val language = "en-US"
    private val page = 1
    private lateinit var recyclerViewPop: RecyclerView
    private lateinit var recyclerViewToday: RecyclerView
    private lateinit var recyclerViewAir: RecyclerView
    private lateinit var recyclerViewTop: RecyclerView
    private lateinit var today: TextView
    private lateinit var air: TextView
    private lateinit var top: TextView
    private lateinit var popHomeAdapter: PopHomeAdapter
    private lateinit var frontItemsAdapter: FrontItemsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var progressBar: ProgressBar
    private lateinit var linearLayout: LinearLayout

    private lateinit var frontPopInterface: FrontPopInterface
    private lateinit var frontAirInterface: FrontAirInterface
    private lateinit var frontTodayInterface: FrontTodayInterface
    private lateinit var frontTopInterface: FrontTopInterface
    private lateinit var selectedItemInterface: SelectedItemInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerViewAir = view.findViewById(R.id.recyclerAirHome)
        recyclerViewPop = view.findViewById(R.id.recyclerPopHome)
        recyclerViewToday = view.findViewById(R.id.recyclerNowHome)
        recyclerViewTop = view.findViewById(R.id.recyclerTopHome)
        progressBar = view.findViewById(R.id.progressHome)
        linearLayout = view.findViewById(R.id.linearAll)
        today = view.findViewById(R.id.txtToday)
        air = view.findViewById(R.id.txtAir)
        top = view.findViewById(R.id.txtTopR)
        progressBar.visibility = View.VISIBLE
        linearLayout.visibility = View.GONE
        getPopFront()
        getAirFront()
        getTodayFront()
        getTopFront()
        today.setOnClickListener {
            selectedItemInterface.getToday()
        }
        air.setOnClickListener {
            selectedItemInterface.getAir()
        }
        top.setOnClickListener {
            selectedItemInterface.getTop()
        }
        return view
    }

    private fun getPopFront() {
        frontPopInterface = FrontPopRet.getRetrofit().create(FrontPopInterface::class.java)
        var call: Call<PopularAll> = frontPopInterface.getShows(Constants.key, language, page)
        call.enqueue(object: Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
                linearLayout.visibility = View.GONE
            }

            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                progressBar.visibility = View.GONE
                linearLayout.visibility = View.VISIBLE
                getPopData(response.body()!!.allPopular)
            }

        })

    }

    private fun getPopData(allPopular: ArrayList<Popular>) {
        var activity = activity as Context
        popHomeAdapter = PopHomeAdapter(activity, allPopular)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPop.layoutManager = linearLayoutManager
        recyclerViewPop.adapter = popHomeAdapter
    }

    private fun getAirFront() {
        frontAirInterface = FrontAirRet.getRetrofit().create(FrontAirInterface::class.java)
        var call: Call<PopularAll> = frontAirInterface.getShows(Constants.key, language, page)
        call.enqueue(object: Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
                linearLayout.visibility = View.GONE
            }

            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                progressBar.visibility = View.GONE
                linearLayout.visibility = View.VISIBLE
                getAirData(response.body()!!.allPopular)
            }

        })
    }

    private fun getAirData(allPopular: ArrayList<Popular>) {
        var activity = activity as Context
        frontItemsAdapter = FrontItemsAdapter(activity, allPopular)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewAir.adapter = frontItemsAdapter
        recyclerViewAir.layoutManager = linearLayoutManager
    }

    private fun getTodayFront() {
        frontTodayInterface = FrontTodayRet.getRetrofit().create(FrontTodayInterface::class.java)
        var call: Call<PopularAll> = frontTodayInterface.getShows(Constants.key, language, page)
        call.enqueue(object : Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
                linearLayout.visibility = View.GONE
            }

            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                progressBar.visibility = View.GONE
                linearLayout.visibility = View.VISIBLE
                getTodayData(response.body()!!.allPopular)
            }

        })
    }

    private fun getTodayData(allPopular: ArrayList<Popular>) {
        var activity = activity as Context
        frontItemsAdapter = FrontItemsAdapter(activity, allPopular)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewToday.layoutManager = linearLayoutManager
        recyclerViewToday.adapter = frontItemsAdapter

    }

    private fun getTopFront() {
        frontTopInterface = FrontTopRet.getRetrofit().create(FrontTopInterface::class.java)
        var call: Call<PopularAll> = frontTopInterface.getShows(Constants.key, language, page)
        call.enqueue(object : Callback<PopularAll>{
            override fun onFailure(call: Call<PopularAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
                linearLayout.visibility = View.GONE
            }

            override fun onResponse(call: Call<PopularAll>, response: Response<PopularAll>) {
                progressBar.visibility = View.GONE
                linearLayout.visibility = View.VISIBLE
                getTopData(response.body()!!.allPopular)
            }

        })
    }

    private fun getTopData(allPopular: ArrayList<Popular>) {
        var activity = activity as Context
        frontItemsAdapter = FrontItemsAdapter(activity, allPopular)
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTop.adapter = frontItemsAdapter
        recyclerViewTop.layoutManager = linearLayoutManager

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SelectedItemInterface){
            selectedItemInterface = context
        }
    }
}