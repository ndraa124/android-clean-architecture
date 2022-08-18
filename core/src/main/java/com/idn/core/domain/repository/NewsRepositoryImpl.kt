package com.idn.core.domain.repository

import androidx.paging.PagingData
import com.idn.core.data.source.remote.model.news.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsRepositoryImpl {
    fun getAllNews(search: String? = null): Flow<PagingData<NewsModel>>
}
