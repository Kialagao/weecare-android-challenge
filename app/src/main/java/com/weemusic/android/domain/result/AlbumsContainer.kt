package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class AlbumsContainer(
    @SerializedName("feed")
    val feed: Feed
)