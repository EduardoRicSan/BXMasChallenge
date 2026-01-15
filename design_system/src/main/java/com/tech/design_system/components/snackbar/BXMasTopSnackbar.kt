package com.tech.design_system.components.snackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tech.design_system.common.model.BXMasSnackbarMessage
import com.tech.design_system.common.model.BXMasSnackbarType
import kotlinx.coroutines.delay
import java.util.UUID

@Composable
fun BXMasTopSnackbar(
    snackbarMessage: BXMasSnackbarMessage,
    isVisible: MutableState<Boolean>,
    onDismiss: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val bgColor: Color
    val contentColor: Color
    val icon: ImageVector

    when (snackbarMessage.type) {
        BXMasSnackbarType.Success -> {
            bgColor = Color(0xFF4CAF50)
            contentColor = Color.White
            icon = Icons.Default.CheckCircle
        }

        BXMasSnackbarType.Error -> {
            bgColor = Color(0xFFF44336)
            contentColor = Color.White
            icon = Icons.Default.Error
        }

        BXMasSnackbarType.Warning -> {
            bgColor = Color(0xFFFFC107)
            contentColor = Color.Black
            icon = Icons.Default.Warning
        }

        BXMasSnackbarType.Info -> {
            bgColor = Color(0xFF2196F3)
            contentColor = Color.White
            icon = Icons.Default.Info
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 0.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedVisibility(
            visible = isVisible.value,
            enter = slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
            ) + fadeIn(animationSpec = tween(400)),
            exit = slideOutVertically(
                targetOffsetY = { -it },
                animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
            ) + fadeOut(animationSpec = tween(400))
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(bgColor)
                    .clickable {
                        isVisible.value = false
                        onDismiss?.invoke()
                    }
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(icon, contentDescription = snackbarMessage.type.name, tint = contentColor)
                    Text(
                        text = snackbarMessage.message,
                        color = contentColor,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                snackbarMessage.actionLabel?.let { label ->
                    TextButton(onClick = {
                        snackbarMessage.onAction?.invoke()
                        isVisible.value = false
                        onDismiss?.invoke()
                    }) {
                        Text(label, color = contentColor)
                    }
                }
            }
        }
    }

    val currentKey = remember { mutableStateOf(UUID.randomUUID().toString()) }

    LaunchedEffect(snackbarMessage, currentKey.value) {
        isVisible.value = true
        delay(snackbarMessage.durationMillis)
        if (isVisible.value) {
            isVisible.value = false
            onDismiss?.invoke()
        }
        currentKey.value = UUID.randomUUID().toString()
    }
}
