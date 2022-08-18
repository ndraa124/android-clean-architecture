package com.idn.core.data.repository

import android.content.Context
import androidx.paging.PagingData
import com.idn.core.data.source.remote.datasource.NewsRemoteDataSource
import com.idn.core.data.source.remote.model.news.NewsModel
import com.idn.core.domain.repository.NewsRepositoryImpl
import kotlinx.coroutines.flow.Flow

class NewsRepository(
    private val ctx: Context,
    private val dataSource: NewsRemoteDataSource
) : NewsRepositoryImpl {
    override fun getAllNews(search: String?): Flow<PagingData<NewsModel>> =
        dataSource.getAllNews(search)
}
