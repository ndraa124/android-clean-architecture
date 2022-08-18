package com.idn.news.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.idn.core.data.source.remote.model.news.NewsModel
import com.idn.news.R
import com.idn.news.databinding.ItemNewsBinding
import com.idn.news.external.recyclerview.LoadStateListener

class MainAdapter : PagingDataAdapter<NewsModel, MainAdapter.RecyclerViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<NewsModel>() {
        override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val bind: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_news,
            parent,
            false
        )

        return RecyclerViewHolder(bind)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class RecyclerViewHolder(private val bind: ItemNewsBinding) :
        RecyclerView.ViewHolder(bind.root) {

        fun bind(data: NewsModel) {
            bind.data = data
            bind.executePendingBindings()
        }
    }
}
