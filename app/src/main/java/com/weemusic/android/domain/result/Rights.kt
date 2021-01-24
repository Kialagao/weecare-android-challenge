package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Rights(
    @SerializedName("label")
    val label: String
)