package com.weemusic.android.domain

import com.weemusic.android.network.albumdto.price.Price
import org.threeten.bp.LocalDate

data class Album(
    val id: Int,
    val name: String?,
    val images: List<String>?,
    val rights: String?,
    val title: String?,
    val artist: String?,
    val category: String?,
    val price: String?,
    val releaseDate: LocalDate?
)
