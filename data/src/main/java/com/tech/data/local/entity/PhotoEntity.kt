package com.tech.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tech.data.remote.dto.PhotoDTO

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
    url = url,
    thumbnailUrl = thumbnailUrl
)