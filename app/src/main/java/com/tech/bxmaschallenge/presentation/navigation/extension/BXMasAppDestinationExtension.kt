package com.tech.bxmaschallenge.presentation.navigation.extension

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.tech.bxmaschallenge.presentation.navigation.route.BXMasAppDestination
import com.tech.bxmaschallenge.presentation.navigation.route.PhotoDetail
import com.tech.bxmaschallenge.presentation.navigation.route.PhotoList
import com.tech.design_system.R
import com.tech.design_system.common.model.UiText

fun BXMasAppDestination.toTopBarTitle(): UiText =
    when (this) {
        PhotoList ->  UiText.StringRes(R.string.title_photo_list)
        else -> UiText.StringRes(R.string.title_photo_detail)
    }

fun NavDestination?.toAppDestination(): BXMasAppDestination {
    return when {
        this?.hasRoute<PhotoList>() == true -> PhotoList
        this?.hasRoute<PhotoDetail>() == true -> PhotoDetail
        else -> PhotoList
    } as BXMasAppDestination
}

