package com.idn.core.data.source.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.idn.core.data.source.remote.model.news.NewsModel
import com.idn.core.data.source.remote.network.service.ApiService
import com.idn.core.external.Constant
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val apiService: ApiService,
    private val search: String? = null
) : PagingSource<Int, NewsModel>() {
    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        delay(3000)
        val page = params.key ?: Constant.STARTING_PAGE_INDEX
        return try {
            val res = apiService.getAllNews(page = page, search = search)
            val prev = if (page == Constant.STARTING_PAGE_INDEX) null else page
            val next = if (page != res.totalPages) page.plus(1) else null
            val data = res.data ?: emptyList()

            LoadResult.Page(data, prev, next)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
