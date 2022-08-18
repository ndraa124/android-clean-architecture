package com.idn.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.idn.core.domain.usecase.NewsUseCase

class NewsViewModel(private val useCase: NewsUseCase) : ViewModel() {
    fun getAllNews(search: String? = null) = useCase.getAllNews(search).asLiveData()
}
