package com.weemusic.android.network.albumdto.category


import com.google.gson.annotations.SerializedName

data class CategoryAttributes(
    @SerializedName("im:id")
    val imId: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("term")
    val term: String
)