package com.weemusic.android.network.albumdto.id


import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("attributes")
    val idAttributes: IdAttributes,
    @SerializedName("label")
    val label: String
)