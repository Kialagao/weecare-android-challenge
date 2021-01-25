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


