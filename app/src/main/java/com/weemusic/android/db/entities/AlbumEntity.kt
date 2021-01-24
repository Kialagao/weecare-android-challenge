package com.weemusic.android.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.weemusic.android.domain.Album
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

@Entity(tableName = "albums")
data class AlbumEntity(@PrimaryKey(autoGenerate = false)
    val albumId: String,
    val albumName: String?,
    val artist: String?,
    val category: String?,
    val rights: String?,
    val title: String?,
    val price: Double?,
    val releaseDate: String?
)


fun AlbumEntity.asAlbum(images : List<String>) : Album {
    return Album(
        id = albumId.toInt(),
        name = albumName,
        images = images,
        category = category,
        rights = rights,
        title = title,
        artist = artist,
        price = "$${price.toString()}",
        releaseDate = LocalDate.parse(releaseDate, DateTimeFormatter.ISO_DATE_TIME)
    )
}
/*
"yyyy-MM-dd'T'HH:mm:ss.SSSZ"	2001-07-04T12:08:56.235-0700
"yyyy-MM-dd'T'HH:mm:ss.SSSXXX"	2001-07-04T12:08:56.235-07:00*/

fun AlbumEntity.asAlbumDomainModelFromEntities(images : List<AlbumImageEntity>) : Album {
    return asAlbum(images.asImageUrls())
}


