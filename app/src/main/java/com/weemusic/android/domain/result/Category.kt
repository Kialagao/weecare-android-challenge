package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("attributes")
    val categoryAttributes: CategoryAttributes
)