package com.weemusic.android.network.albumdto


import com.google.gson.annotations.SerializedName
import com.weemusic.android.db.entities.AlbumEntity
import com.weemusic.android.db.entities.AlbumImageEntity
import com.weemusic.android.network.albumdto.image.Image

data class AlbumsContainer(
    @SerializedName("feed")
    val feed: Feed
)

fun AlbumsContainer.asAlbumAndImageEntities() : List<Pair<AlbumEntity, List<AlbumImageEntity>>> {
    return feed.albumDTO.map {
        val albumImageEntities = it.images!!.asAlbumImageEntities(it.id.idAttributes.imId)
        val albumEntity = it.asAlbumEntity()
        Pair(albumEntity, albumImageEntities)
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