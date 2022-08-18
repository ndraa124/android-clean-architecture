package com.idn.core.data.source.remote.network.reponse

sealed class ApiResponseList<out R> {
    data class Success<out T>(
        val msg: String? = null,
        val totalPages: Int? = null,
        val data: T
    ) : ApiResponseList<T>()

    data class Empty(val emptyMessage: String) : ApiResponseList<Nothing>()

    data class Error(val errorMessage: String) : ApiResponseList<Nothing>()

    data class ErrorConnection(val errorMessage: String) : ApiResponseList<Nothing>()
}
