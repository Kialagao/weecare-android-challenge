package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class ContentType(
    @SerializedName("attributes")
    val contentTypeAttributes: ContentTypeAttributes,
    @SerializedName("im:contentType")
    val imContentType: ImContentType
)