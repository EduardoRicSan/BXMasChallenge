package com.tech.domain.useCase

import com.tech.domain.repository.BXMasRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val bxMasRepository: BXMasRepository
) {
    suspend operator fun invoke() = bxMasRepository.getPhotos()

}