package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class ArtistAttributes(
    @SerializedName("href")
    val href: String
)