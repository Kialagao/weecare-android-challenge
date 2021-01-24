package com.weemusic.android.network.albumdto.price


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("attributes")
    val priceAttributes: PriceAttributes,
    @SerializedName("label")
    val label: String
)