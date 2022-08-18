package com.idn.core.data.boundresource

import android.content.Context
import com.idn.core.R
import com.idn.core.data.source.remote.network.reponse.ApiResponseObject
import com.idn.core.vom.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

abstract class ObjectBoundResource<ResultType, RequestType>(val context: Context) {
    private var result: Flow<Resource<ResultType>> =
        flow {
            emit(Resource.LoadingShow())

            if (checkConnection()) {
                when (val apiResponse = dataCreate().firstOrNull()) {
                    is ApiResponseObject.Success -> {
                        emit(Resource.LoadingHide())
                        emit(
                            dataCallback(apiResponse.data).let {
                                Resource.Success(apiResponse.msg, data = it)
                            }
                        )
                    }
                    is ApiResponseObject.Empty -> {
                        emit(Resource.LoadingHide())
                        emit(Resource.Empty(apiResponse.msg))
                    }
                    is ApiResponseObject.Error -> {
                        emit(Resource.LoadingHide())
                        emit(Resource.Error(apiResponse.msg))
                    }
                    else -> {
                        emit(Resource.LoadingHide())
                        emit(Resource.Error(context.resources.getString(R.string.error)))
                    }
                }
            } else {
                emit(Resource.LoadingHide())
                emit(Resource.ErrorConnection(context.resources.getString(R.string.error_no_connection)))
            }
        }

    protected abstract fun checkConnection(): Boolean

    protected abstract fun dataCallback(data: RequestType): ResultType

    protected abstract fun dataCreate(): Flow<ApiResponseObject<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}
