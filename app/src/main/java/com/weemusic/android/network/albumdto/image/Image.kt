package com.weemusic.android.network.albumdto.image


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("attributes")
    val imageAttributes: ImageAttributes,
    @SerializedName("label")
    val label: String
)