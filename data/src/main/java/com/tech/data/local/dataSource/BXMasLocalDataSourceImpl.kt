package com.tech.data.local.dataSource

import com.tech.data.local.dao.BXMasDao
import com.tech.data.local.entity.PhotoEntity
import jakarta.inject.Inject

class BXMasLocalDataSourceImpl @Inject constructor(
    private val photoDao: BXMasDao
) : BXMasLocalDataSource {

    override suspend fun clear() {
        photoDao.clear()
    }

    override suspend fun insertAll(photos: List<PhotoEntity>) {
        photoDao.insertAll(photos)
    }

    override suspend fun getPagedPhotos(
        limit: Int,
        offset: Int
    ): List<PhotoEntity> =
        photoDao.getPagedPhotos(limit, offset)

    override suspend fun getCount(): Int =
        photoDao.count()
}
