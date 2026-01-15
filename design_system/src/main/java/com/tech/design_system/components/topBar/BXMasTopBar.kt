package com.tech.design_system.components.topBar

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow


/**
 * BXMasTopBar
 * Top bar with optional back button, title, and two optional action icons.
 *
 * @param titleText Text to display as the title (can use custom text composable)
 * @param showBackButton Whether to show the back button
 * @param onBackClick Lambda invoked when back button is clicked
 * @param firstActionIcon Optional first action icon (e.g., notifications)
 * @param showFirstActionIcon Whether to display first action icon
 * @param onFirstActionClick Lambda invoked when first action is clicked
 * @param secondActionIcon Optional second action icon (e.g., support)
 * @param showSecondActionIcon Whether to display second action icon
 * @param onSecondActionClick Lambda invoked when second action is clicked
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BXMasTopBar(
    titleText: String,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    firstActionIcon: ImageVector? = null,
    showFirstActionIcon: Boolean = true,
    onFirstActionClick: () -> Unit = {},
    secondActionIcon: ImageVector? = null,
    showSecondActionIcon: Boolean = true,
    onSecondActionClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = titleText,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        actions = {
            if (firstActionIcon != null && showFirstActionIcon) {
                IconButton(onClick = onFirstActionClick) {
                    Icon(
                        imageVector = firstActionIcon,
                        contentDescription = "First action",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            if (secondActionIcon != null && showSecondActionIcon) {
                IconButton(onClick = onSecondActionClick) {
                    Icon(
                        imageVector = secondActionIcon,
                        contentDescription = "Second action",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            scrolledContainerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier.statusBarsPadding() // para que la barra no tape la status bar
    )
}
