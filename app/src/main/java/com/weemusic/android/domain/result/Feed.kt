package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("author")
    val author: Author,
    @SerializedName("entry")
    val entry: List<Entry>
)