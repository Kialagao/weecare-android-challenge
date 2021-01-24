package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("attributes")
    val idAttributes: IdAttributes,
    @SerializedName("label")
    val label: String
)