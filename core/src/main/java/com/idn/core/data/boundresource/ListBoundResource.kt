package com.idn.core.data.boundresource

import android.content.Context
import com.idn.core.R
import com.idn.core.data.source.remote.network.reponse.ApiResponseList
import com.idn.core.vom.Resource
import kotlinx.coroutines.flow.*

abstract class ListBoundResource<ResultType, RequestType>(val ctx: Context) {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.LoadingShow())

        if (checkConnection()) {
            when (val apiResponse = dataCreate().firstOrNull()) {
                is ApiResponseList.Success -> {
                    emit(Resource.LoadingHide())
                    emitAll(
                        dataCallback(apiResponse.data).map {
                            Resource.Success(apiResponse.msg, apiResponse.totalPages, it)
                        }
                    )
                }
                is ApiResponseList.Empty -> {
                    emit(Resource.LoadingHide())
                    emit(Resource.Empty(apiResponse.emptyMessage))
                }
                is ApiResponseList.Error -> {
                    emit(Resource.LoadingHide())
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                else -> {
                    emit(Resource.LoadingHide())
                    emit(Resource.Error(ctx.resources.getString(R.string.error)))
                }
            }
        } else {
            emit(Resource.LoadingHide())
            emit(Resource.ErrorConnection(ctx.resources.getString(R.string.error_no_connection)))
        }
    }

    protected abstract fun checkConnection(): Boolean

    protected abstract fun dataCallback(data: RequestType): Flow<ResultType>

    protected abstract fun dataCreate(): Flow<ApiResponseList<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}
