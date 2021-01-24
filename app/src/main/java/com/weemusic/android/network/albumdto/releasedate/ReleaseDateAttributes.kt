package com.weemusic.android.network.albumdto.releasedate


import com.google.gson.annotations.SerializedName

data class ReleaseDateAttributes(
    @SerializedName("label")
    val label: String
)