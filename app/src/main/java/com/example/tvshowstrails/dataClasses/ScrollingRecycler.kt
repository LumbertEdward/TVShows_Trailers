package com.example.tvshowstrails.dataClasses

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.concurrent.fixedRateTimer

abstract class ScrollingRecycler(private val gridLayoutManager: RecyclerView.LayoutManager): RecyclerView.OnScrollListener() {
    private val layoutManager: RecyclerView.LayoutManager = gridLayoutManager

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (layoutManager is LinearLayoutManager){
            var visibleItemPosition: Int = layoutManager.childCount
            var totalItemCount: Int = layoutManager.itemCount
            var firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
            if (!isLastPage() && !isLoading()){
                if ((visibleItemPosition + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0){
                    loadMoreItems()
                }
            }
        }
        else if (layoutManager is GridLayoutManager){
            var visibleItemPosition: Int = layoutManager.childCount
            var totalItemCount: Int = layoutManager.itemCount
            var fistVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
            if (!isLoading() && !isLastPage()){
                if ((visibleItemPosition + totalItemCount) >= totalItemCount && fistVisibleItemPosition >= 0){
                    loadMoreItems()
                }
            }

        }
    }
    abstract fun loadMoreItems(): Unit
    abstract fun isLoading(): Boolean
    abstract fun isLastPage(): Boolean
}