package com.idn.core.domain.usecase

import androidx.paging.PagingData
import com.idn.core.data.source.remote.model.news.NewsModel
import com.idn.core.domain.repository.NewsRepositoryImpl
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val repo: NewsRepositoryImpl) : NewsUseCase {
    override fun getAllNews(search: String?): Flow<PagingData<NewsModel>> =
        repo.getAllNews(search)
}
