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
import com.example.tvshowstrails.dataClasses.Season
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class SeasonAdapter(private val context: Context, private val list: ArrayList<Season>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var selectedItemInterface: SelectedItemInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.season_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.title.text = list[position].name
        val picasso: Picasso.Builder = Picasso.Builder(context)
        picasso.downloader(OkHttp3Downloader(context))
        picasso.build().load("https://image.tmdb.org/t/p/w342//" + list[position].poster_path).into(myViewHolder.img)
        myViewHolder.linearLayout.setOnClickListener {
            selectedItemInterface.getSeasonEpisodes(list[position])
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (context is SelectedItemInterface){
            selectedItemInterface = context
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img: ImageView = itemView.findViewById(R.id.imgSeas)
        val title: TextView = itemView.findViewById(R.id.titleSeas)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linSeas)
    }
}