package com.tech.design_system.common.model

import androidx.compose.ui.graphics.ImageBitmap

/**
 * Unified model of Image source
 */
sealed class BXMasImageSource {
    data class Url(val url: String) : BXMasImageSource()
    data class Resource(val resId: Int) : BXMasImageSource()
    data class Bitmap(val imageBitmap: ImageBitmap) : BXMasImageSource()
}