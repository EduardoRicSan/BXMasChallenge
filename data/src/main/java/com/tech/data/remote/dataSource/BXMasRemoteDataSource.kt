package com.tech.data.remote.dataSource

import com.tech.core.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

interface BXMasRemoteDataSource {
    suspend fun syncPhotos(): Flow<NetworkResult<Unit>>
}