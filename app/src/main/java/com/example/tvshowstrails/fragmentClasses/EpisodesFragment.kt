package com.example.tvshowstrails.fragmentClasses

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.R
import com.example.tvshowstrails.adapterClasses.EpisodesAdapter
import com.example.tvshowstrails.dataClasses.Episodes
import com.example.tvshowstrails.dataClasses.EpisodesAll
import com.example.tvshowstrails.dataClasses.Season
import com.example.tvshowstrails.interfaceClasses.EpisodesInterface
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface
import com.example.tvshowstrails.retrofitClasses.EpisodesRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodesFragment : Fragment() {
    private val key: String = "bfb9dfa7c6ccc29b4bdee1ec785dcb7c"
    private val language = "en-US"
    private val check: String = "CHECK"
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var back: ImageView
    private lateinit var filter: ImageView
    private lateinit var title: TextView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var episodesAdapter: EpisodesAdapter
    private lateinit var episodesInterface: EpisodesInterface
    private lateinit var selectedItemInterface: SelectedItemInterface
    private var bundle: Bundle? = null
    private var season: Season? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = this.arguments
        if (bundle != null){
            season = bundle!!.getParcelable("EPISODE")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_episodes, container, false)
        recyclerView = view.findViewById(R.id.recyclerEpisodes)
        progressBar = view.findViewById(R.id.progressEpisode)
        progressBar.visibility = View.GONE
        back = view.findViewById(R.id.backEpisode)
        filter = view.findViewById(R.id.filterEpisode)
        title = view.findViewById(R.id.titleEpisode)
        val activity = activity as Context
        gridLayoutManager = GridLayoutManager(activity, 3)
        getData()
        back.setOnClickListener {
            selectedItemInterface.onBackPressed()
        }
        return view
    }

    private fun getData() {
        var id: Int = season!!.id!!
        var numb: Int = season!!.season_number!!
        var txt: String = numb.toString()
        title.text = "Season $txt"
        episodesInterface = EpisodesRetrofit.getRetrofit().create(EpisodesInterface::class.java)
        val call: Call<EpisodesAll> = episodesInterface.getEpisodesData(id, numb, key, language)
        call.enqueue(object : Callback<EpisodesAll>{
            override fun onFailure(call: Call<EpisodesAll>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
                Log.d(check, t.toString())
            }
            override fun onResponse(call: Call<EpisodesAll>, response: Response<EpisodesAll>) {
                progressBar.visibility = View.GONE
                retrieveData(response.body()!!.episodes)
            }
        })
    }

    private fun retrieveData(episodes: ArrayList<Episodes>) {
        val activity = activity as Context
        episodesAdapter = EpisodesAdapter(activity, episodes, season!!.poster_path!!)
        recyclerView.adapter = episodesAdapter
        recyclerView.layoutManager = gridLayoutManager
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SelectedItemInterface){
            selectedItemInterface = context
        }
    }
}