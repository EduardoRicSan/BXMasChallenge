package com.tech.domain.repository


import com.tech.core.network.mapList
import com.tech.core.remote.NetworkResult
import com.tech.data.remote.dataSource.BXMasRemoteDataSource
import com.tech.domain.model.PhotoUIModel
import com.tech.domain.model.toPhoto
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class BXMasRepositoryImpl @Inject constructor(
    private val bxMasRemoteDataSource: BXMasRemoteDataSource,
) : BXMasRepository {
    override suspend fun getPhotos(): Flow<NetworkResult<List<PhotoUIModel>>> =
        bxMasRemoteDataSource.getPhotos().map { it.mapList { it.toPhoto() }
        }.flowOn(Dispatchers.IO)

}