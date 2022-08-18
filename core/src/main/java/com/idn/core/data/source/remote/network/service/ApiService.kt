package com.idn.core.data.source.remote.network.service

import com.idn.core.data.source.remote.model.news.NewsModel
import com.idn.core.data.source.remote.model.resmodel.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("berita")
    suspend fun getAllNews(
        @Query("search") search: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null
    ): ListResponse<NewsModel>
}
