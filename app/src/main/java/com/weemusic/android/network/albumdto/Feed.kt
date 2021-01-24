package com.weemusic.android.network.albumdto


import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("entry")
    val albumDTO: List<AlbumDTO>
)