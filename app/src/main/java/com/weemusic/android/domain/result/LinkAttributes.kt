package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class LinkAttributes(
    @SerializedName("href")
    val href: String,
    @SerializedName("rel")
    val rel: String,
    @SerializedName("type")
    val type: String
)