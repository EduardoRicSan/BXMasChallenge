package com.tech.data.remote.dataSource

import com.tech.core.remote.NetworkResult
import com.tech.data.remote.dto.PhotoDTO
import kotlinx.coroutines.flow.Flow

interface BXMasRemoteDataSource {
    suspend fun getPhotos(): Flow<NetworkResult<List<PhotoDTO>>>
}