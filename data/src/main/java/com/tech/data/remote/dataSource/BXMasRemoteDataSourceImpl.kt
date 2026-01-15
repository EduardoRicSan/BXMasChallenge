package com.tech.data.remote.dataSource

import com.tech.core.network.safeApiCall
import com.tech.core.remote.NetworkResult
import com.tech.data.remote.api.BXMasApiService
import com.tech.data.remote.dto.PhotoDTO
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class BXMasRemoteDataSourceImpl @Inject constructor(
    private val bXMasApiService: BXMasApiService,
) : BXMasRemoteDataSource {

    override suspend fun getPhotos(): Flow<NetworkResult<List<PhotoDTO>>> = safeApiCall {
        bXMasApiService.getPhotos()
    }.flowOn(Dispatchers.IO)

}