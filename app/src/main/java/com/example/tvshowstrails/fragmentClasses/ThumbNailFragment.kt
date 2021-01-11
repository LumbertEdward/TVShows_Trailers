package com.example.tvshowstrails.fragmentClasses

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.R
import com.example.tvshowstrails.adapterClasses.ThumbNailAdapter
import com.example.tvshowstrails.dataClasses.Popular
import com.example.tvshowstrails.dataClasses.Trailers
import com.example.tvshowstrails.dataClasses.TrailersAll
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface
import com.example.tvshowstrails.interfaceClasses.TrailersInterface
import com.example.tvshowstrails.retrofitClasses.TrailersRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThumbNailFragment : Fragment() {
    private val key: String = "bfb9dfa7c6ccc29b4bdee1ec785dcb7c"
    private val language = "en-US"
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var trailersInterface: TrailersInterface
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var thumbNailAdapter: ThumbNailAdapter
    private lateinit var relativeLayout: RelativeLayout
    private var popular: Popular? = null
    private var bundle: Bundle? = null
    private lateinit var selectedItemInterface: SelectedItemInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = this.arguments
        if (bundle != null){
            popular = bundle!!.getParcelable("TRAILER")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_thumb_nail, container, false)
        imageView = view.findViewById(R.id.backThumb)
        textView = view.findViewById(R.id.titleThumb)
        recyclerView = view.findViewById(R.id.recyclerThumb)
        progressBar = view.findViewById(R.id.progressThumb)
        relativeLayout = view.findViewById(R.id.relAll)
        relativeLayout.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        var activity = activity as Context
        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        getThumb()
        imageView.setOnClickListener {
            selectedItemInterface.onBackPressed()
        }
        return view
    }

    private fun getThumb() {
        val id: Int = popular!!.id!!
        textView.text = popular!!.name
        trailersInterface = TrailersRetrofit.getRetrofit().create(TrailersInterface::class.java)
        var call: Call<TrailersAll> = trailersInterface.getTrailersData(id, key, language)
        call.enqueue(object : Callback<TrailersAll>{
            override fun onFailure(call: Call<TrailersAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
                relativeLayout.visibility = View.GONE
            }

            override fun onResponse(call: Call<TrailersAll>, response: Response<TrailersAll>) {
                progressBar.visibility = View.GONE
                relativeLayout.visibility = View.VISIBLE
                getData(response.body()!!.results)
            }

        })
    }

    private fun getData(results: ArrayList<Trailers>) {
        thumbNailAdapter = ThumbNailAdapter(activity!!, results, popular!!.name!!)
        recyclerView.adapter = thumbNailAdapter
        recyclerView.layoutManager = linearLayoutManager
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SelectedItemInterface){
            selectedItemInterface = context
        }
    }
}