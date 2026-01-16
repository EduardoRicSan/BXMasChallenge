package com.tech.domain.useCase

import com.tech.domain.model.PhotoUIModel
import com.tech.domain.repository.BXMasRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPhotoByIdUseCase @Inject constructor(
    private val repository: BXMasRepository
) {
    operator fun invoke(photoId: Int): Flow<PhotoUIModel?> =
        repository.getPhotoById(photoId)
}
