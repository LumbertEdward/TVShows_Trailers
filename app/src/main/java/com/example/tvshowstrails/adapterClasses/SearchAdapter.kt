package com.example.tvshowstrails.adapterClasses

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.PopularAdapter
import com.example.tvshowstrails.R
import com.example.tvshowstrails.dataClasses.Popular
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class SearchAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private val list: ArrayList<Popular> = ArrayList()
    private lateinit var popularFiltered: ArrayList<Popular>
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private val item: Int = 1
    private val loading: Int = 0
    private lateinit var selectedItemInterface: SelectedItemInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        var layoutInflater: LayoutInflater = LayoutInflater.from(context)
        when(viewType){
            item -> {
                var view: View = layoutInflater.inflate(R.layout.search_item, parent, false)
                viewHolder = MyViewHolder(view)
            }
            loading -> {
                val view: View = layoutInflater.inflate(R.layout.loading_item, parent, false)
                viewHolder = LoadingVH(view)
            }
        }
        return viewHolder!!
    }

    override fun getItemCount(): Int {
       return  list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size - 1 && isLastPage){
            loading
        }
        else{
            item
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            item ->{
                var myViewHolder: MyViewHolder = holder as MyViewHolder
                myViewHolder.textView.text = list[position].name
                var picasso: Picasso.Builder = Picasso.Builder(context)
                picasso.downloader(OkHttp3Downloader(context))
                picasso.build().load("https://image.tmdb.org/t/p/w342//" + list[position].poster_path).into(myViewHolder.imageView)
                myViewHolder.cardView.setOnClickListener {
                    selectedItemInterface.passId(list[position])
                }

            }
            loading ->{
                val loadingVH: LoadingVH = holder as LoadingVH
                loadingVH.progress.visibility = View.VISIBLE

            }
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (context is SelectedItemInterface){
            selectedItemInterface = context
        }
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cardView: CardView = itemView.findViewById(R.id.cardSearch)
        val imageView: ImageView = itemView.findViewById(R.id.imgSearch)
        val textView: TextView = itemView.findViewById(R.id.txtSearchTitle)
    }

    class LoadingVH(itemView: View): RecyclerView.ViewHolder(itemView){
        val progress = itemView.findViewById<ProgressBar>(R.id.progressLoading)!!
    }

    fun add(popular: Popular){
        list.add(popular)
        notifyItemInserted(list.size -1)
    }
    fun addAll(popularList: ArrayList<Popular>){
        for (popular: Popular in popularList){
            list.add(popular)
        }
        popularFiltered = ArrayList(popularList)
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var myList: ArrayList<Popular> = ArrayList()
                if (constraint.isNullOrBlank()){
                    myList.addAll(popularFiltered)
                }
                var string: String = constraint.toString().toLowerCase().trim()
                for (popular: Popular in popularFiltered){
                    if (popular.name.toString().toLowerCase().contains(string)){
                        myList.add(popular)
                    }
                }
                var filterResults: FilterResults = FilterResults()
                filterResults.values = myList
                return filterResults

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                list.clear()
                list.addAll(results!!.values as ArrayList<Popular>)
                notifyDataSetChanged()
            }

        }
    }
    private fun getItem(position: Int): Popular{
        return list[position]
    }
    fun addFooter(): Unit{
        isLoading = true
        add(Popular())
    }
    fun removeFooter(): Unit{
        isLoading = false
        var position = list.size - 1
        var popular: Popular = getItem(position)
        if (!popular.equals(null)){
            list.removeAt(position)
            notifyItemRemoved(position)
        }

    }

}