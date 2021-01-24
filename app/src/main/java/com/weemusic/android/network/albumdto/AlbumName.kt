package com.weemusic.android.network.albumdto


import com.google.gson.annotations.SerializedName

data class AlbumName(
    @SerializedName("label")
    val label: String
)