package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class ImContentType(
    @SerializedName("attributes")
    val imContentTypeAttributes: ImContentTypeAttributes
)