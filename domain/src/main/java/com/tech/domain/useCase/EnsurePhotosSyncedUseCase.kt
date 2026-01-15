package com.tech.domain.useCase

import com.tech.core.remote.NetworkResult
import com.tech.domain.repository.BXMasRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EnsurePhotosSyncedUseCase @Inject constructor(
    private val repository: BXMasRepository
) {

    suspend operator fun invoke(): Flow<NetworkResult<Unit>> = flow {
        val total = repository.getTotalPhotos()

        if (total > 0) {
            emit(NetworkResult.Success(Unit))
            return@flow
        }

        repository.syncPhotos().collect { emit(it) }
    }
}