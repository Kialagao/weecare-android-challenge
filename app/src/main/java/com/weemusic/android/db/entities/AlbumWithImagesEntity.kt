package com.weemusic.android.db.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.weemusic.android.domain.Album
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

data class AlbumWithImagesEntity(
    @Embedded val albumEntity: AlbumEntity,
    @Relation(
        parentColumn = "albumId",
        entityColumn = "parentAlbumId"
    )
    val imageEntities: List<AlbumImageEntity>
)

fun List<AlbumWithImagesEntity>.asAlbumListDomainModelFromDB() : List<Album> {
    return map {
        val images = it.imageEntities.asImageUrls()
        it.albumEntity.asAlbumDomainModelFromUrls(images)
    }
}

fun List<Pair<AlbumEntity, List<AlbumImageEntity>>>.asAlbumListDomainModelFromNetWork() : List<Album> {
    return map {
        val album = it.first
        Album(
            album.albumId.toInt(),
            album.albumName,
            it.second.asImageUrls(),
            album.rights,
            album.title,
            album.artist,
            album.category,
            "$${album.price.toString()}",
            LocalDate.parse(album.releaseDate, DateTimeFormatter.ISO_DATE_TIME)
        )
    }
}