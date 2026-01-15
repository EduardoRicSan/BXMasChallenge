package com.tech.domain.useCase

import com.tech.domain.model.PhotoUIModel
import com.tech.domain.repository.BXMasRepository
import javax.inject.Inject

class GetPagedPhotosUseCase @Inject constructor(
    private val repository: BXMasRepository
) {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int
    ): List<PhotoUIModel> =
        repository.getPagedPhotos(page, pageSize)
}