package com.example.tvshowstrails.adapterClasses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.R
import com.example.tvshowstrails.dataClasses.Genre
import com.example.tvshowstrails.dataClasses.Genres
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface

class GenreDetailAdapter(private val context: Context, private val list: ArrayList<Genres>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var selectedItemInterface: SelectedItemInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.genre_detail_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.name.text = list[position].name
        myViewHolder.relativeLayout.setOnClickListener {
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
        val name: TextView = itemView.findViewById(R.id.nameGenre)
        val relativeLayout: RelativeLayout = itemView.findViewById(R.id.relGen)
    }
}