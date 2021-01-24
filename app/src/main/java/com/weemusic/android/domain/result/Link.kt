package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("attributes")
    val linkAttributes: LinkAttributes
)