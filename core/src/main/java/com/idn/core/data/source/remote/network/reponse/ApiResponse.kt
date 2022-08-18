package com.idn.core.data.source.remote.network.reponse

sealed class ApiResponse<out R> {
    data class Success(val successMessage: String) : ApiResponse<Nothing>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
}
