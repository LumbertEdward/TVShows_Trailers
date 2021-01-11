package com.example.tvshowstrails.fragmentClasses

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.tvshowstrails.R
import com.example.tvshowstrails.dataClasses.Episodes
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface

class EpisodeDetails : Fragment() {
    private lateinit var title: TextView
    private lateinit var back: ImageView
    private lateinit var play: ImageView
    private lateinit var img: ImageView
    private lateinit var progressBar: ProgressBar
    private var episodes: Episodes? = null
    private var bundle: Bundle? = null
    private lateinit var selectedItemInterface: SelectedItemInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = this.arguments
        if (bundle != null){
            episodes = bundle!!.getParcelable("EPISODEDETAILS")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_episode_details, container, false)
        title = view.findViewById(R.id.titleEpDet)
        back = view.findViewById(R.id.backEpDet)
        play = view.findViewById(R.id.play)
        img = view.findViewById(R.id.imgEp)
        progressBar = view.findViewById(R.id.progressEpDet)
        progressBar.visibility = View.GONE
        back.setOnClickListener {
            selectedItemInterface.onBackPressed()
        }
        setData()
        return view
    }

    private fun setData() {
        title.text = episodes!!.name

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SelectedItemInterface){
            selectedItemInterface = context
        }
    }
}