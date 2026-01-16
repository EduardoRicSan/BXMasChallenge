package com.tech.data.local.dataSource

import com.tech.data.local.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface BXMasLocalDataSource {

    suspend fun clear()

    fun getPhotoById(photoId: Int): Flow<PhotoEntity?>

    suspend fun insertAll(photos: List<PhotoEntity>)

    suspend fun getPagedPhotos(
        limit: Int,
        offset: Int
    ): List<PhotoEntity>

    suspend fun getCount(): Int
}