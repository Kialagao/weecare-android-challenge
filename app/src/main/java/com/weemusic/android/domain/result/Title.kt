package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("label")
    val label: String
)