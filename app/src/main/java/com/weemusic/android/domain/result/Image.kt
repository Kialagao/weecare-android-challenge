package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("attributes")
    val imageAttributes: ImageAttributes,
    @SerializedName("label")
    val label: String
)