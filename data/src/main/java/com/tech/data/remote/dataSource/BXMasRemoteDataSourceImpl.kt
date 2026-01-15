package com.tech.data.remote.dataSource

import com.tech.core.network.safeApiCall
import com.tech.core.remote.NetworkResult
import com.tech.data.local.dataSource.BXMasLocalDataSource
import com.tech.data.local.entity.toEntity
import com.tech.data.remote.api.BXMasApiService
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class BXMasRemoteDataSourceImpl @Inject constructor(
    private val api: BXMasApiService,
    private val localDataSource: BXMasLocalDataSource
) : BXMasRemoteDataSource {

    override suspend fun syncPhotos(): Flow<NetworkResult<Unit>> =
        safeApiCall {
            api.getPhotos()
        }.map { result ->
            when (result) {
                is NetworkResult.Loading ->
                    NetworkResult.Loading

                is NetworkResult.Error ->
                    NetworkResult.Error(result.message)

                is NetworkResult.Success -> {
                    localDataSource.clear()
                    localDataSource.insertAll(
                        result.data.map { it.toEntity() }
                    )
                    NetworkResult.Success(Unit)
                }
            }
        }.flowOn(Dispatchers.IO)
}
