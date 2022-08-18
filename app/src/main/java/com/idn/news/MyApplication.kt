package com.idn.news

import android.app.Application
import com.idn.core.di.datasourceRemoteModule
import com.idn.core.di.networkModule
import com.idn.core.di.repositoryModule
import com.idn.news.di.useCaseModule
import com.idn.news.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            androidFileProperties()
            modules(
                listOf(
                    networkModule,
                    datasourceRemoteModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
