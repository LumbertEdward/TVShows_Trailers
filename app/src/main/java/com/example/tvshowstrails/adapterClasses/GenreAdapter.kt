package com.example.tvshowstrails.adapterClasses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.R
import com.example.tvshowstrails.dataClasses.Genres
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface

class GenreAdapter(private val context: Context, private val list: ArrayList<Genres>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var selectedItemInterface: SelectedItemInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.genre_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.textView.text = list[position].name
        myViewHolder.imageView.setOnClickListener {
            selectedItemInterface.getGenreDetailsData(list[position])
        }
        myViewHolder.cardView.setOnClickListener {
            selectedItemInterface.getGenreDetailsData(list[position])
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (context is SelectedItemInterface){
            selectedItemInterface = context
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.genreTitle)
        val imageView: ImageView = itemView.findViewById(R.id.more)
        val cardView: CardView = itemView.findViewById(R.id.cardGen)
    }
}