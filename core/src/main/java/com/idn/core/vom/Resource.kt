package com.idn.core.vom

abstract class Resource<T>(
    val message: String? = null,
    val totalPages: Int? = null,
    val data: T? = null
) {
    class LoadingShow<T> : Resource<T>()
    class LoadingHide<T> : Resource<T>()
    class Empty<T>(msg: String? = null) : Resource<T>(msg)
    class Error<T>(msg: String? = null) : Resource<T>(msg)
    class ErrorConnection<T>(msg: String? = null) : Resource<T>(msg)
    class Success<T>(msg: String? = null, totalPages: Int? = null, data: T? = null) :
        Resource<T>(msg, totalPages, data)
}
