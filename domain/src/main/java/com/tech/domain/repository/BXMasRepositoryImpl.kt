package com.tech.domain.repository


import com.tech.core.remote.NetworkResult
import com.tech.data.local.dataSource.BXMasLocalDataSource
import com.tech.data.remote.dataSource.BXMasRemoteDataSource
import com.tech.domain.model.PhotoUIModel
import com.tech.domain.model.toUI
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BXMasRepositoryImpl @Inject constructor(
    private val remote: BXMasRemoteDataSource,
    private val local: BXMasLocalDataSource
) : BXMasRepository {

    override suspend fun syncPhotos(): Flow<NetworkResult<Unit>> =
        remote.syncPhotos()

    override suspend fun getPagedPhotos(
        page: Int,
        pageSize: Int
    ): List<PhotoUIModel> =
        local.getPagedPhotos(
            limit = pageSize,
            offset = page * pageSize
        ).map { it.toUI() }

    override suspend fun getTotalPhotos(): Int =
        local.getCount()

    override fun getPhotoById(id: Int): Flow<PhotoUIModel?> =
        local.getPhotoById(id).map { it?.toUI() }
}
