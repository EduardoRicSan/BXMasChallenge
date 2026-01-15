package com.tech.domain.repository

import com.tech.core.remote.NetworkResult
import com.tech.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface BXMasRepository {
    suspend fun getPhotos(): Flow<NetworkResult<List<Photo>>>
}