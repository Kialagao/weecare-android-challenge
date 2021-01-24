package com.weemusic.android.network.albumdto.releasedate


import com.google.gson.annotations.SerializedName

data class ReleaseDate(
    @SerializedName("attributes")
    val releaseDateAttributes: ReleaseDateAttributes,
    @SerializedName("label")
    val label: String
)