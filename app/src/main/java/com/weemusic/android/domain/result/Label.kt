package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Label(
    @SerializedName("label")
    val label: String
)