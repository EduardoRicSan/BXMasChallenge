package com.tech.bxmaschallenge.presentation.navigation.route

import com.tech.domain.model.PhotoUIModel
import kotlinx.serialization.Serializable

@Serializable
sealed interface BXMasAppDestination

@Serializable
data object PhotoList : BXMasAppDestination

@Serializable
data class PhotoDetail(
    val photo: PhotoUIModel,
) : BXMasAppDestination