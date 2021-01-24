package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class ContentTypeAttributes(
    @SerializedName("label")
    val label: String,
    @SerializedName("term")
    val term: String
)