package com.tech.domain.repository

import com.tech.core.remote.NetworkResult
import com.tech.domain.model.PhotoUIModel
import kotlinx.coroutines.flow.Flow

interface BXMasRepository {

    suspend fun syncPhotos(): Flow<NetworkResult<Unit>>

    suspend fun getPagedPhotos(
        page: Int,
        pageSize: Int
    ): List<PhotoUIModel>

    suspend fun getTotalPhotos(): Int

    fun getPhotoById(id: Int): Flow<PhotoUIModel?>
}
