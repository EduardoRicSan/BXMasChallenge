package com.tech.design_system.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.tech.design_system.common.model.BXMasImageSource
import com.tech.design_system.common.viewmodel.CoilViewModel

/**
 * Generic image loader using Coil (AsyncImage).
 * Supports placeholders, errors and scaling.
 */
@Composable
fun BXMasImage(
    source: BXMasImageSource,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    placeholder: Painter? = null,
    error: Painter? = null,
    onClick: (() -> Unit)? = null,
) {
    val clickableModifier = if (onClick != null) modifier.clickable { onClick() } else modifier

    when (source) {
        is BXMasImageSource.Url -> {
            val viewModel = hiltViewModel<CoilViewModel>()
            val imageLoader = viewModel.imageLoader

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(source.url)
                    .crossfade(true)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = contentDescription,
                modifier = clickableModifier,
                contentScale = contentScale,
                placeholder = placeholder,
                error = error
            )
        }

        is BXMasImageSource.Resource -> {
            Image(
                painter = painterResource(id = source.resId),
                contentDescription = contentDescription,
                modifier = clickableModifier,
                contentScale = contentScale
            )
        }

        is BXMasImageSource.Bitmap -> {
            Image(
                bitmap = source.imageBitmap,
                contentDescription = contentDescription,
                modifier = clickableModifier,
                contentScale = contentScale
            )
        }
    }
}

@Composable
fun BXMasCircularImage(
    source: BXMasImageSource,
    size: Dp = 60.dp,
    contentDescription: String? = null,
    borderWidth: Dp? = 1.dp,
    borderColor: Color = Color.LightGray ,
    shape: Shape = CircleShape,
    onClick: (() -> Unit)? = null
) {
    val modifier = Modifier
        .size(size)
        .clip(shape)
        .then(
            if (borderWidth != null && borderColor != Color.Unspecified)
                Modifier.border(borderWidth, borderColor, shape)
            else Modifier
        )
        .then(
            if (onClick != null) Modifier.clickable { onClick() } else Modifier
        )

    BXMasImage(
        source = source,
        modifier = modifier,
        contentDescription = contentDescription
    )
}