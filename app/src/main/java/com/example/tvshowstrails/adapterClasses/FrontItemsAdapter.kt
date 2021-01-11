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
import com.example.tvshowstrails.dataClasses.Popular
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class FrontItemsAdapter(private val context: Context, private val list: ArrayList<Popular>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private lateinit var selectedItemInterface: SelectedItemInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.fron_items, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.title.text = list[position].name
        myViewHolder.rate.text = list[position].vote_average.toString()
        var picasso: Picasso.Builder = Picasso.Builder(context)
        picasso.downloader(OkHttp3Downloader(context))
        picasso.build().load("https://image.tmdb.org/t/p/w342//" + list[position].poster_path).into(myViewHolder.imageView)
        myViewHolder.linearLayout.setOnClickListener {
            selectedItemInterface.passId(list[position])
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (context is SelectedItemInterface){
            selectedItemInterface = context
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imageView: ImageView = itemView.findViewById(R.id.imgFront)
        var title: TextView = itemView.findViewById(R.id.titleFront)
        var rate: TextView = itemView.findViewById(R.id.PopularityFront)
        var linearLayout: LinearLayout = itemView.findViewById(R.id.linearFront)
    }
}