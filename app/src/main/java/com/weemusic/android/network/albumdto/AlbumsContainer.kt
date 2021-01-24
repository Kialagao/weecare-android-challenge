package com.weemusic.android.network.albumdto


import com.google.gson.annotations.SerializedName
import com.weemusic.android.db.entities.AlbumEntity
import com.weemusic.android.db.entities.AlbumImageEntity
import com.weemusic.android.network.albumdto.image.Image

data class AlbumsContainer(
    @SerializedName("feed")
    val feed: Feed
)

fun AlbumsContainer.asAlbumEntities() : List<AlbumEntity> {
    return feed.albumDTO.map {
        AlbumEntity(
            albumId = it.id.label,
            albumName = it.name?.label,
            artist = it.artist?.label,
            category = it.category?.categoryAttributes?.label,
            rights = it.rights?.label,
            title = it.title?.label,
            releaseDate = it.releaseDate?.releaseDateAttributes?.label)
    }
}

fun AlbumsContainer.asAlbumAndImageEntities() : List<Pair<AlbumEntity, List<AlbumImageEntity>>> {
    return feed.albumDTO.map {
        val albumImageEntities = it.images!!.asAlbumImageEntities(it.id.idAttributes.imId)
        val albumEntity = it.asAlbumEntity()
        Pair(albumEntity, albumImageEntities)
    }
}

fun List<Image>.asImageUrls() : List<String> {
    return this.map {
        it.label
    }
}

fun List<Image>.asAlbumImageEntities(parentAlbumId : String) : List<AlbumImageEntity> {
    return this.map {
        AlbumImageEntity(
            imageId = 0,
            parentAlbumId = parentAlbumId,
            imageUrl = it.label
        )
    }
}