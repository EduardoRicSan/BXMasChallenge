package com.tech.core.route

import kotlinx.serialization.Serializable

@Serializable
sealed interface BXMasAppDestination

@Serializable
data object PhotoList : BXMasAppDestination

@Serializable
data class PhotoDetail(
    val photoId: Int,
) : BXMasAppDestination