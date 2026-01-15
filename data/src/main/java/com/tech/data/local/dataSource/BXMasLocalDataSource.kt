package com.tech.data.local.dataSource

import com.tech.data.local.entity.PhotoEntity

interface BXMasLocalDataSource {

    suspend fun clear()

    suspend fun insertAll(photos: List<PhotoEntity>)

    suspend fun getPagedPhotos(
        limit: Int,
        offset: Int
    ): List<PhotoEntity>

    suspend fun getCount(): Int
}