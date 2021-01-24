package com.weemusic.android.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.weemusic.android.domain.Album
import org.threeten.bp.LocalDate

@Entity(tableName = "images")
data class AlbumImageEntity(
    @PrimaryKey(autoGenerate = true)
    val imageId: Long,
    val parentAlbumId: String,
    val imageUrl: String
)

fun List<AlbumImageEntity>.asImageUrls() : List<String> {
    return map {
        it.imageUrl
    }
}

fun AlbumImageEntity.asImageUrl() : String{
    return imageUrl
}



