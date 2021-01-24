package com.weemusic.android.db.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.weemusic.android.domain.Album

data class AlbumWithImagesEntity(
    @Embedded val albumEntity: AlbumEntity,
    @Relation(
        parentColumn = "albumId",
        entityColumn = "parentAlbumId"
    )
    val imageEntities: List<AlbumImageEntity>
)

fun List<AlbumWithImagesEntity>.asAlbums() : List<Album> {
    return map {
        val images = it.imageEntities.asImageUrls()
        it.albumEntity.asAlbum(images)
    }
}