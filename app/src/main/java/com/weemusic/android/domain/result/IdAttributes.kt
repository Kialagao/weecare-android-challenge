package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class IdAttributes(
    @SerializedName("im:id")
    val imId: String
)