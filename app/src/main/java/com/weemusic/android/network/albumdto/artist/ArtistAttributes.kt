package com.weemusic.android.network.albumdto.artist


import com.google.gson.annotations.SerializedName

data class ArtistAttributes(
    @SerializedName("href")
    val href: String
)