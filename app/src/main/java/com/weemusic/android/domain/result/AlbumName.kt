package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class AlbumName(
    @SerializedName("label")
    val label: String
)