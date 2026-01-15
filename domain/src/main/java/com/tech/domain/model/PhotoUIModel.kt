package com.tech.domain.model

import com.tech.data.remote.dto.PhotoDTO
import kotlinx.serialization.Serializable

@Serializable
data class PhotoUIModel(
    var albumId: Int? = null,
    var id: Int? = null,
    var title: String? = null,
    var url: String? = null,
    var thumbnailUrl: String? = null
)

fun PhotoDTO.toPhoto(): PhotoUIModel = PhotoUIModel(
    albumId = albumId,
    id = id,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)
