package com.idn.core.data.source.remote.model.resmodel

data class ObjectResponse<Model>(
    val success: Boolean,
    val message: String,
    val data: Model
)
