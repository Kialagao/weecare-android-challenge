package com.weemusic.android.network.albumdto.category


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("attributes")
    val categoryAttributes: CategoryAttributes
)