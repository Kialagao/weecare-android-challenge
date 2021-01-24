package com.weemusic.android.network.albumdto.price


import com.google.gson.annotations.SerializedName

data class PriceAttributes(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String
)