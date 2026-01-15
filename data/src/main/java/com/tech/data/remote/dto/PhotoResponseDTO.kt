package com.tech.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoDTO(
    @SerialName("albumId") var albumId: Int? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("title") var title: String? = null,
    @SerialName("url") var url: String? = null,
    @SerialName("thumbnailUrl") var thumbnailUrl: String? = null
)