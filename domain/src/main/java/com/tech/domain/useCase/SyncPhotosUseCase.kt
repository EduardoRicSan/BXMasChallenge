package com.tech.domain.useCase

import com.tech.core.remote.NetworkResult
import com.tech.domain.repository.BXMasRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class SyncPhotosUseCase @Inject constructor(
    private val repository: BXMasRepository
) {
    suspend operator fun invoke(): Flow<NetworkResult<Unit>> =
        repository.syncPhotos()
}