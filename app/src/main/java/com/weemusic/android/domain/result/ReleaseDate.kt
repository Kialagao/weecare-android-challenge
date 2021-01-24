package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class ReleaseDate(
    @SerializedName("attributes")
    val releaseDateAttributes: ReleaseDateAttributes,
    @SerializedName("label")
    val label: String
)