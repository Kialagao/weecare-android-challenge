package com.weemusic.android.network.albumdto.id


import com.google.gson.annotations.SerializedName

data class IdAttributes(
    @SerializedName("im:id")
    val imId: String
)