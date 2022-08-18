package com.idn.core.data.source.remote.network.config

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.idn.core.BuildConfig
import com.idn.core.data.source.remote.network.service.ApiService
import com.idn.core.external.Constant.CONNECT_TIME_OUT
import com.idn.core.external.Constant.READ_TIME_OUT
import com.idn.core.external.Constant.WRITE_TIME_OUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {
    private fun chuckerInterceptor(context: Context): ChuckerInterceptor = run {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .alwaysReadResponseBody(true)
            .build()
    }

    fun provideHttpLoggingInterceptor(context: Context): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            apply {
                if (BuildConfig.DEBUG) {
                    this.level = HttpLoggingInterceptor.Level.BODY
                } else {
                    this.level = HttpLoggingInterceptor.Level.NONE
                }
            }
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chuckerInterceptor(context))
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    fun getApiClient(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
