package com.idn.core.data.source.remote.model.resmodel

data class ListResponse<Model>(
    val success: Boolean,
    val message: String,
    val totalPages: Int,
    val data: List<Model>?
)
