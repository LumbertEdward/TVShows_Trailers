package com.example.tvshowstrails.fragmentClasses

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.tvshowstrails.R
import com.example.tvshowstrails.dataClasses.Constants
import com.example.tvshowstrails.dataClasses.Trailers
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.google.android.youtube.player.YouTubePlayerView

class VideoFragment : Fragment() {
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private var trailers: Trailers? = null
    private var bundle: Bundle? = null
    private var youTubePlayerSupportFragment: YouTubePlayerSupportFragment? = null
    private lateinit var selectedItemInterface: SelectedItemInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = this.arguments
        if (bundle != null){
            trailers = bundle!!.getParcelable("VIDEO")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_video, container, false)
        imageView = view.findViewById(R.id.backVideo)
        textView = view.findViewById(R.id.titleVideo)
        imageView.setOnClickListener {
            selectedItemInterface.onBackPressed()
        }
        getVideo()
        return view
    }

    private fun getVideo() {
        var activity = activity as Context
        textView.text = trailers!!.name
        youTubePlayerSupportFragment = YouTubePlayerSupportFragment()
        val fragmentTransaction: FragmentTransaction = getActivity()!!.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameVideo, youTubePlayerSupportFragment!!)
        fragmentTransaction.commit()
        youTubePlayerSupportFragment!!.initialize(Constants.YouTubeKey, object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1!!.cueVideo(trailers!!.key)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SelectedItemInterface){
            selectedItemInterface = context
        }
    }
}