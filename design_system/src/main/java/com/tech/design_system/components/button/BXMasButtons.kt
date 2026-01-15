package com.tech.design_system.components.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * BXMasPrimaryButton - Main Call-to-Action
 * Usage: For the primary action on the screen. Highlighted with a solid background.
 * Customizable: text, text color, background color, corner radius, font size, font weight.
 */
@Composable
fun BXMasPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    cornerRadius: Dp = 12.dp,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
    ) {
        Text(text = text, fontSize = fontSize, fontWeight = fontWeight, color = textColor)
    }
}

/**
 * BXMasSecondaryButton - Outlined style
 * Usage: For secondary actions. Less prominent, outlined button with transparent background.
 * Customizable: text, text color, corner radius, font size, font weight.
 */
@Composable
fun BXMasSecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    cornerRadius: Dp = 12.dp,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    textColor: Color = MaterialTheme.colorScheme.onSecondary
) {
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(cornerRadius),
        modifier = modifier
    ) {
        Text(text = text, fontSize = fontSize, fontWeight = fontWeight, color = textColor)
    }
}
