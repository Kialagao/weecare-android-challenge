package com.weemusic.android.network.albumdto


import com.google.gson.annotations.SerializedName
import com.weemusic.android.db.entities.AlbumEntity
import com.weemusic.android.network.albumdto.artist.Artist
import com.weemusic.android.network.albumdto.category.Category
import com.weemusic.android.network.albumdto.id.Id
import com.weemusic.android.network.albumdto.image.Image
import com.weemusic.android.network.albumdto.link.Link
import com.weemusic.android.network.albumdto.price.Price
import com.weemusic.android.network.albumdto.releasedate.ReleaseDate

data class AlbumDTO(
    @SerializedName("category")
    val category: Category?,
    @SerializedName("id")
    val id: Id,
    @SerializedName("im:artist")
    val artist: Artist?,
    @SerializedName("im:image")
    val images: List<Image>?,
    @SerializedName("im:name")
    val name: AlbumName?,
    @SerializedName("im:price")
    val price: Price?,
    @SerializedName("im:releaseDate")
    val releaseDate: ReleaseDate?,
    @SerializedName("link")
    val link: Link?,
    @SerializedName("rights")
    val rights: Rights?,
    @SerializedName("title")
    val title: Title?
)

fun AlbumDTO.asAlbumEntity() : AlbumEntity {
    return AlbumEntity(
        albumId = id.idAttributes.imId,
        albumName = name?.label,
        artist = artist?.label,
        category = category?.categoryAttributes?.label,
        rights = rights?.label,
        title = title?.label,
        releaseDate = releaseDate?.releaseDateAttributes?.label)
}