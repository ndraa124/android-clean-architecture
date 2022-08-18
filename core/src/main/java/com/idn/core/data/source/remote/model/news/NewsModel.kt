package com.idn.core.data.source.remote.model.news

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    @SerializedName("br_id")
    val id: Int,

    @SerializedName("br_judul")
    val title: String,

    @SerializedName("br_created_at")
    val date: String,

    @SerializedName("br_isi")
    val desc: String,

    @SerializedName("br_gambar")
    val img: String
) : Parcelable {
    constructor() : this(0, "", "", "", "")
}
