package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class PriceAttributes(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String
)