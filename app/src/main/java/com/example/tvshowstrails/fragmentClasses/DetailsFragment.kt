package com.example.tvshowstrails.fragmentClasses

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.R
import com.example.tvshowstrails.adapterClasses.GenreDetailAdapter
import com.example.tvshowstrails.adapterClasses.SeasonAdapter
import com.example.tvshowstrails.dataClasses.Constants
import com.example.tvshowstrails.dataClasses.Details
import com.example.tvshowstrails.dataClasses.Popular
import com.example.tvshowstrails.interfaceClasses.DetailsInterface
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface
import com.example.tvshowstrails.retrofitClasses.DetailsRetrofit
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment : Fragment() {
    private val language = "en-US"
    private lateinit var detailsInterface: DetailsInterface
    private lateinit var selectedItemInterface: SelectedItemInterface
    private var bundle: Bundle? = null
    private lateinit var back: ImageView
    private lateinit var img: ImageView
    private lateinit var play: ImageView
    //private lateinit var thumb: ImageView
    private lateinit var title: TextView
    private lateinit var rate: TextView
    private lateinit var overview: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var scrollView: ScrollView
    private lateinit var first: TextView
    private var popular: Popular? = null
    private lateinit var genreDetailAdapter: GenreDetailAdapter
    private lateinit var seasonAdapter: SeasonAdapter
    private lateinit var genreRec: RecyclerView
    private lateinit var seasRec: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var linearLayoutManagerSeas: LinearLayoutManager
    private lateinit var numb: TextView
    private lateinit var last: TextView
    private lateinit var next: TextView
    private val strn: String = "LOG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         bundle = this.arguments
        if (bundle != null){
            popular = bundle!!.getParcelable("DATA")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_details, container, false)
        back = view.findViewById(R.id.imgBackDet)
        img = view.findViewById(R.id.imgDet)
        title = view.findViewById(R.id.titleDet)
        rate = view.findViewById(R.id.rateDet)
        overview = view.findViewById(R.id.overviewDet)
        progressBar = view.findViewById(R.id.progressDet)
        scrollView = view.findViewById(R.id.scrollDet)
        genreRec = view.findViewById(R.id.genreDet)
        seasRec = view.findViewById(R.id.seasonsGenre)
        first = view.findViewById(R.id.firstDate)
        numb = view.findViewById(R.id.seasonsNumber)
        last = view.findViewById(R.id.lastDate)
        next = view.findViewById(R.id.nextDate)
        play = view.findViewById(R.id.playVid)
        scrollView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        back.setOnClickListener {
            selectedItemInterface.onBackPressed()
        }
        play.setOnClickListener {
            selectedItemInterface.passThumb(popular)
        }
        getData()
        return view
    }

    private fun getData() {
        var id: Int = popular?.id!!
        detailsInterface = DetailsRetrofit.getRetrofit().create(DetailsInterface::class.java)
        var call: Call<Details> = detailsInterface.getDetailsData(id, Constants.AllConstants.key, language)
        call.enqueue(object: Callback<Details>{
            override fun onFailure(call: Call<Details>, t: Throwable) {
                progressBar.visibility = View.VISIBLE
                var activity = activity as Context
                Toast.makeText(activity, t.toString(), Toast.LENGTH_LONG).show()
                Log.d(strn, t.toString())
                scrollView.visibility = View.GONE
            }

            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                progressBar.visibility = View.GONE
                scrollView.visibility = View.VISIBLE
                getDetails(response.body())
            }

        })
    }

    private fun getDetails(body: Details?) {
        var activity = activity as Context
        var picasso: Picasso.Builder = Picasso.Builder(activity)
        picasso.downloader(OkHttp3Downloader(activity))
        picasso.build().load("https://image.tmdb.org/t/p/w342//" + popular?.poster_path).into(img)
        title.text = popular?.name
        rate.text = popular?.vote_average.toString()
        overview.text = popular?.overview
        first.text = body!!.firstAirDate
        var tot: String = body!!.numberOfSeasons.toString()
        numb.text = "Seasons ($tot)"
        last.text = body!!.lastAirDate
        /**if (body!!.nextEpisodeToAir!!.name != null){
            val nm = body!!.nextEpisodeToAir!!.name
            val out = "Title: $nm"
            val dat = body!!.nextEpisodeToAir!!.air_date
            if (dat != null){
                next.text = "Date: $dat $out"
            }
            else{
                next.text = ""
            }
        }**/



        seasonAdapter = SeasonAdapter(activity, body!!.seasons)
        genreDetailAdapter = GenreDetailAdapter(activity, body!!.genres)

        linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        genreRec.layoutManager = linearLayoutManager
        genreRec.adapter = genreDetailAdapter

        linearLayoutManagerSeas = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        seasRec.layoutManager = linearLayoutManagerSeas
        seasRec.adapter = seasonAdapter

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SelectedItemInterface)
            selectedItemInterface = context
    }
}