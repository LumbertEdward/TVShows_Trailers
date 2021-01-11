package com.example.tvshowstrails.adapterClasses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.R
import com.example.tvshowstrails.dataClasses.Constants
import com.example.tvshowstrails.dataClasses.Trailers
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView

class ThumbNailAdapter(private val context: Context, private val list: ArrayList<Trailers>, private val name: String): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private lateinit var selectedItemInterface: SelectedItemInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View = LayoutInflater.from(context).inflate(R.layout.thumb_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var myViewHolder: MyViewHolder = holder as MyViewHolder
        myViewHolder.textView.text = list[position].name
        myViewHolder.thumbnailView.initialize(Constants.YouTubeKey, object : YouTubeThumbnailView.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubeThumbnailView?,
                p12: YouTubeThumbnailLoader?
            ) {
                p12!!.setVideo(list[position].key)
                p12.setOnThumbnailLoadedListener(object : YouTubeThumbnailLoader.OnThumbnailLoadedListener{
                    override fun onThumbnailLoaded(p0: YouTubeThumbnailView?, p1: String?) {
                        p12.release()
                    }

                    override fun onThumbnailError(
                        p0: YouTubeThumbnailView?,
                        p1: YouTubeThumbnailLoader.ErrorReason?
                    ) {
                        Toast.makeText(context, "Not Loaded", Toast.LENGTH_LONG).show()
                    }

                })
            }

            override fun onInitializationFailure(
                p0: YouTubeThumbnailView?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
            }

        })
        myViewHolder.cardView.setOnClickListener {
            selectedItemInterface.passTrailer(list[position])

        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (context is SelectedItemInterface){
            selectedItemInterface = context
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val thumbnailView: YouTubeThumbnailView = itemView.findViewById(R.id.thumb)
        val textView: TextView = itemView.findViewById(R.id.thumb_title)
        val cardView: CardView = itemView.findViewById(R.id.card_thumb)
    }
}