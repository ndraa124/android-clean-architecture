package com.idn.core.data.source.remote.datasource

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.idn.core.data.source.remote.model.news.NewsModel
import com.idn.core.data.source.remote.network.service.ApiService
import com.idn.core.data.source.remote.pagingsource.NewsPagingSource
import com.idn.core.external.Constant
import kotlinx.coroutines.flow.Flow

class NewsRemoteDataSource(private val apiService: ApiService, private val ctx: Context) {
    fun getAllNews(search: String? = null): Flow<PagingData<NewsModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { NewsPagingSource(apiService, search) }
        ).flow
    }
}
