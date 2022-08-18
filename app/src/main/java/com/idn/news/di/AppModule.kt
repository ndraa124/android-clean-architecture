package com.idn.news.di

import com.idn.core.domain.usecase.NewsInteractor
import com.idn.core.domain.usecase.NewsUseCase
import com.idn.news.viewmodel.NewsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { NewsViewModel(get()) }
}
