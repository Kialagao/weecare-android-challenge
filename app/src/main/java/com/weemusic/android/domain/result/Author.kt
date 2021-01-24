package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("name")
    val name: Label,
    @SerializedName("uri")
    val uri: Uri
)