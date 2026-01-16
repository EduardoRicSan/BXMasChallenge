package com.tech.bxmaschallenge.presentation.navigation.extension

import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import com.tech.core.route.BXMasAppDestination
import com.tech.core.route.PhotoDetail
import com.tech.core.route.PhotoList
import com.tech.design_system.R
import com.tech.design_system.common.model.UiText

// Converts a BXMasAppDestination to a UiText for the top bar title
fun BXMasAppDestination.toTopBarTitle(): UiText =
    when (this) {
        PhotoList ->  UiText.StringRes(R.string.title_photo_list)
        else -> UiText.StringRes(R.string.title_photo_detail)
    }

// Maps a NavBackStackEntry to a BXMasAppDestination
fun NavBackStackEntry.toAppDestination(): BXMasAppDestination? {
    return when {
        toRouteOrNull<PhotoList>() != null -> PhotoList
        toRouteOrNull<PhotoDetail>() != null -> PhotoDetail(photoId = -1)
        else -> null
    }
}

// Safely attempts to convert the NavBackStackEntry route to a type T
inline fun <reified T : Any> NavBackStackEntry.toRouteOrNull(): T? {
    return runCatching {
        toRoute<T>()
    }.getOrNull()
}



