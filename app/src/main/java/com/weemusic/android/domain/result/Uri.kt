package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Uri(
    @SerializedName("label")
    val label: String
)