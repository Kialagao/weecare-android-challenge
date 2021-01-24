package com.weemusic.android.network.albumdto.artist


import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("attributes")
    val artistAttributes: ArtistAttributes,
    @SerializedName("label")
    val label: String
)