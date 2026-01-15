package com.tech.design_system.common.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed interface UiText {
    data class StringRes(
        val resId: Int
    ) : UiText

    data class Dynamic(
        val value: String
    ) : UiText
}

@Composable
fun UiText.asString(): String =
    when (this) {
        is UiText.StringRes -> stringResource(id = resId)
        is UiText.Dynamic -> value
    }