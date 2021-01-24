package com.weemusic.android.network.albumdto.link


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("attributes")
    val linkAttributes: LinkAttributes
)