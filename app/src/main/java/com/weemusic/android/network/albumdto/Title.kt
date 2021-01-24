package com.weemusic.android.network.albumdto


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("label")
    val label: String
)