package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class ImContentTypeAttributes(
    @SerializedName("label")
    val label: String,
    @SerializedName("term")
    val term: String
)