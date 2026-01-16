package com.tech.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tech.data.local.entity.ImageServerDummyUrl.SERVER_OK_BASE_URL
import com.tech.data.local.entity.ImageServerDummyUrl.SERVER_OK_BASE_URL_IMAGE_SIZE
import com.tech.data.remote.dto.PhotoDTO

object ImageServerDummyUrl {
    const val SERVER_OK_BASE_URL = "https://picsum.photos/seed/"
    const val SERVER_OK_BASE_URL_IMAGE_SIZE = "600/400"
}

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey val id: Int,
    val albumId: Int,
    val title: String?,
    val url: String?,
    val thumbnailUrl: String?,
)
fun PhotoDTO.toEntity() = PhotoEntity(
    albumId = albumId ?: 0,
    id = id ?: 0,
    title = title,
    url = randomPhotoUrl(id ?: 0),
    thumbnailUrl = randomPhotoUrl(id ?: 0)
)


fun randomPhotoUrl(id: Int): String {
    return "$SERVER_OK_BASE_URL$id/$SERVER_OK_BASE_URL_IMAGE_SIZE"
}