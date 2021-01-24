package com.weemusic.android.network.albumdto


import com.google.gson.annotations.SerializedName

data class Rights(
    @SerializedName("label")
    val label: String
)