package com.example.tvshowstrails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowstrails.dataClasses.Popular
import com.example.tvshowstrails.interfaceClasses.SelectedItemInterface
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class PopularAdapter(private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private val tvList: ArrayList<Popular> = ArrayList()
    private lateinit var tvFiltered: ArrayList<Popular>
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private val item: Int = 1
    private val loading: Int = 0
    private lateinit var selectedItemInterface: SelectedItemInterface

    init {
        //tvList = ArrayList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val layoutInflater = LayoutInflater.from(context)
        when(viewType){
            item -> {
                val view: View = layoutInflater.inflate(R.layout.popular_item, parent, false)
                viewHolder = PopularVH(view)
            }
            loading -> {
                val view: View = layoutInflater.inflate(R.layout.loading_item, parent, false)
                viewHolder = LoadingVH(view)
            }
        }
        return viewHolder!!
    }

    override fun getItemCount(): Int {
       return tvList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == tvList.size - 1 && isLastPage){
            loading
        }
        else{
            item
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            item -> {
                val popularVH: PopularVH = holder as PopularVH
                var x = tvList[position].vote_average.toString()
                if (x.isNullOrEmpty()){
                    popularVH.linearLayout.visibility = View.GONE
                }
                else {
                    popularVH.linearLayout.visibility = View.VISIBLE
                    popularVH.txt.text = tvList[position].name
                    popularVH.txtPop.text = tvList[position].vote_average.toString()
                    val picasso: Picasso.Builder = Picasso.Builder(context);
                    picasso.downloader(OkHttp3Downloader(context))
                    picasso.build().load("https://image.tmdb.org/t/p/w342//" + tvList[position].poster_path).into(popularVH.popImage)
                    popularVH.linearLayout.setOnClickListener {
                        selectedItemInterface.passId(tvList[position])
                    }
                }
            }
            loading -> {
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

    class PopularVH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val popImage = itemView.findViewById<ImageView>(R.id.imgPopular)!!
        val txt = itemView.findViewById<TextView>(R.id.titlePopular)!!
        val txtPop = itemView.findViewById<TextView>(R.id.popularPopularity)!!
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linearPop)
    }
    class LoadingVH(itemView: View): RecyclerView.ViewHolder(itemView){
        val progress = itemView.findViewById<ProgressBar>(R.id.progressLoading)!!
    }

    private fun addPopular(popular: Popular): Unit{
        tvList.add(popular)
        notifyItemInserted(tvList.size - 1)
    }
    fun addAllData(popList: ArrayList<Popular>){
        for (popular: Popular in popList){
            tvList.add(popular)
        }
        tvFiltered = ArrayList(popList)
    }
    private fun getItem(position: Int): Popular{
        return tvList[position]
    }
    fun addFooter(): Unit{
        isLoading = true
        addPopular(Popular())
    }
    fun removeFooter(): Unit{
        isLoading = false
        var position = tvList.size - 1
        var popular: Popular = getItem(position)
        if (!popular.equals(null)){
            tvList.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    override fun getFilter(): Filter {
        return object: Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var filterList: ArrayList<Popular> = ArrayList()
                if (constraint.isNullOrBlank()){
                    filterList.addAll(tvFiltered)
                }
                else{
                    var string: String = constraint.toString().toLowerCase().trim()
                    for (popular: Popular in tvFiltered){
                        if (popular.name.toString().toLowerCase().contains(string)){
                            filterList.add(popular)
                        }
                    }
                }
                var filterResults: FilterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                tvList.clear()
                tvList.addAll(results!!.values as ArrayList<Popular>)
                notifyDataSetChanged()
            }

        }
    }

}