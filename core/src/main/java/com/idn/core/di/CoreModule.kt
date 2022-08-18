package com.idn.core.di

import com.idn.core.data.repository.NewsRepository
import com.idn.core.data.source.remote.datasource.NewsRemoteDataSource
import com.idn.core.data.source.remote.network.config.ApiConfig
import com.idn.core.domain.repository.NewsRepositoryImpl
import org.koin.dsl.module

val networkModule = module {
    single { ApiConfig().provideHttpLoggingInterceptor(get()) }
    single { ApiConfig().getApiClient(get()) }
}

val datasourceRemoteModule = module {
    single { NewsRemoteDataSource(get(), get()) }
}

val repositoryModule = module {
    single<NewsRepositoryImpl> { NewsRepository(get(), get()) }
}
