package com.example.tvshowstrails.adapterClasses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.R
import com.example.tvshowstrails.dataClasses.Episodes
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class EpisodesAdapter(private val context: Context, private val list: ArrayList<Episodes>, private val string: String): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var selectedItemInterface: SelectedItemInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.episode_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.txt.text = list[position].name
        myViewHolder.numb.text = "Episode " + list[position].episode_number.toString()
        val picasso: Picasso.Builder = Picasso.Builder(context)
        picasso.downloader(OkHttp3Downloader(context))
        picasso.build().load("https://image.tmdb.org/t/p/w342//" + string).into(myViewHolder.popImage)
        myViewHolder.linearLayout.setOnClickListener {
            selectedItemInterface.getEpisodeData(list[position])
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (context is SelectedItemInterface){
            selectedItemInterface = context
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val popImage: ImageView = itemView.findViewById(R.id.imgEpisode)!!
        val txt: TextView = itemView.findViewById(R.id.titleEpisode)!!
        val numb: TextView = itemView.findViewById(R.id.numberEpisode)!!
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linearEp)
    }
}