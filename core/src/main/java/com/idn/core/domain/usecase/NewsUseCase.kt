package com.idn.core.domain.usecase

import androidx.paging.PagingData
import com.idn.core.data.source.remote.model.news.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews(search: String? = null): Flow<PagingData<NewsModel>>
}
