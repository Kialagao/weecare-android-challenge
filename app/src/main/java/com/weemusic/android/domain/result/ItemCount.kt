package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class ItemCount(
    @SerializedName("label")
    val label: String
)