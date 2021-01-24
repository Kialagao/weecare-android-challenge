package com.weemusic.android.domain.result


import com.google.gson.annotations.SerializedName

data class Entry(
    @SerializedName("category")
    val category: Category,
    @SerializedName("id")
    val id: Id,
    @SerializedName("im:artist")
    val artist: Artist,
    @SerializedName("im:contentType")
    val contentType: ContentType,
    @SerializedName("im:image")
    val images: List<Image>,
    @SerializedName("im:itemCount")
    val itemCount: ItemCount,
    @SerializedName("im:name")
    val name: AlbumName,
    @SerializedName("im:price")
    val price: Price,
    @SerializedName("im:releaseDate")
    val releaseDate: ReleaseDate,
    @SerializedName("link")
    val link: Link,
    @SerializedName("rights")
    val rights: Rights,
    @SerializedName("title")
    val title: Title

)