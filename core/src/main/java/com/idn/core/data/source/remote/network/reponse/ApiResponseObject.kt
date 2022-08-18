package com.idn.core.data.source.remote.network.reponse

sealed class ApiResponseObject<out R> {
    data class Success<out T>(val msg: String? = null, val data: T) : ApiResponseObject<T>()
    data class Empty(val msg: String) : ApiResponseObject<Nothing>()
    data class Error(val msg: String) : ApiResponseObject<Nothing>()
    data class ErrorConnection(val msg: String) : ApiResponseObject<Nothing>()
}
