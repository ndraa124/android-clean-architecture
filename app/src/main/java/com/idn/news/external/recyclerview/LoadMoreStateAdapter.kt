package com.idn.news.external.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.idn.news.databinding.ItemLoadingBinding

class LoadMoreStateAdapter : LoadStateAdapter<LoadMoreStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        val bind = ItemLoadingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(bind)
    }

    inner class LoadStateViewHolder(private val bind: ItemLoadingBinding) :
        RecyclerView.ViewHolder(bind.root) {

        fun bind(loadState: LoadState) {
            bind.loadStateProgress.isVisible = loadState is LoadState.Loading
        }
    }
}
