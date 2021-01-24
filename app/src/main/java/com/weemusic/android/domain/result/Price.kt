package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("attributes")
    val priceAttributes: PriceAttributes,
    @SerializedName("label")
    val label: String
)