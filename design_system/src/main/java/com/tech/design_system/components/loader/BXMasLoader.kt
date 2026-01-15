package com.tech.design_system.components.loader

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.LoadingIndicatorDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


// ---------------- Simple LoadingIndicator ----------------
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BXMasSimpleLoadingIndicator(
    onClose: (() -> Unit)? = null
) {

    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000),
            repeatMode = RepeatMode.Restart
        )
    )
    LoadingIndicator(progress = { progress }, modifier = Modifier.size(60.dp))

}

// ---------------- Custom LoadingIndicator ----------------
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BXMasLoadingIndicator(
    color: Color = MaterialTheme.colorScheme.primary,
    onClose: (() -> Unit)? = null
) {

    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2500),
            repeatMode = RepeatMode.Restart
        )
    )
    LoadingIndicator(
        progress = { progress },
        color = color,
        polygons = LoadingIndicatorDefaults.IndeterminateIndicatorPolygons,
        modifier = Modifier.size(80.dp)
    )

}