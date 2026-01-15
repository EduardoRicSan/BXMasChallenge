package com.tech.design_system.components.text

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * HeadlineText - Very large headers, used for sections or emphasis
 */
@Composable
fun BXMasHeadlineText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    fontWeight: FontWeight? = null,
    fontSize: TextUnit? = null,
    color: Color? = null,
    textAlign: TextAlign = TextAlign.Start,
    letterSpacing: TextUnit? = null,
    lineHeight: TextUnit? = null,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge.copy(
            fontWeight = fontWeight ?: MaterialTheme.typography.headlineLarge.fontWeight,
            fontSize = fontSize ?: MaterialTheme.typography.headlineLarge.fontSize,
            color = color ?: MaterialTheme.typography.headlineLarge.color,
            letterSpacing = letterSpacing ?: MaterialTheme.typography.headlineLarge.letterSpacing,
            lineHeight = lineHeight ?: MaterialTheme.typography.headlineLarge.lineHeight
        ),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
        modifier = modifier.then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    )
}

/**
 * TitleText - Large titles, used for main headers
 */
@Composable
fun BXMasTitleText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    fontWeight: FontWeight? = null,
    fontSize: TextUnit? = null,
    color: Color? = null,
    textAlign: TextAlign = TextAlign.Start,
    letterSpacing: TextUnit? = null,
    lineHeight: TextUnit? = null,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = fontWeight ?: MaterialTheme.typography.titleLarge.fontWeight,
            fontSize = fontSize ?: MaterialTheme.typography.titleLarge.fontSize,
            color = color ?: MaterialTheme.typography.titleLarge.color,
            letterSpacing = letterSpacing ?: MaterialTheme.typography.titleLarge.letterSpacing,
            lineHeight = lineHeight ?: MaterialTheme.typography.titleLarge.lineHeight
        ),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
        modifier = modifier.then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    )
}

/**
 * SubtitleText - Secondary titles or headings
 */
@Composable
fun BXMasSubtitleText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    fontWeight: FontWeight? = null,
    fontSize: TextUnit? = null,
    color: Color? = null,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    letterSpacing: TextUnit? = null,
    lineHeight: TextUnit? = null,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = fontWeight ?: MaterialTheme.typography.titleMedium.fontWeight,
            fontSize = fontSize ?: MaterialTheme.typography.titleMedium.fontSize,
            textDecoration = textDecoration,
            color = color ?: MaterialTheme.typography.titleMedium.color,
            letterSpacing = letterSpacing ?: MaterialTheme.typography.titleMedium.letterSpacing,
            lineHeight = lineHeight ?: MaterialTheme.typography.titleMedium.lineHeight
        ),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
        modifier = modifier.then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    )
}

/**
 * BodyText - Paragraphs or descriptions
 */
@Composable
fun BXMasBodyText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    fontWeight: FontWeight? = null,
    fontSize: TextUnit? = null,
    color: Color? = null,
    textAlign: TextAlign = TextAlign.Start,
    letterSpacing: TextUnit? = null,
    lineHeight: TextUnit? = null,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = fontWeight ?: MaterialTheme.typography.bodyMedium.fontWeight,
            fontSize = fontSize ?: MaterialTheme.typography.bodyMedium.fontSize,
            color = color ?: MaterialTheme.typography.bodyMedium.color,
            letterSpacing = letterSpacing ?: MaterialTheme.typography.bodyMedium.letterSpacing,
            lineHeight = lineHeight ?: MaterialTheme.typography.bodyMedium.lineHeight
        ),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
        modifier = modifier.then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    )
}

/**
 * CaptionText - Small labels, notes, or metadata
 */
@Composable
fun BXMasCaptionText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    fontWeight: FontWeight? = null,
    fontSize: TextUnit? = null,
    color: Color? = null,
    textAlign: TextAlign = TextAlign.Start,
    letterSpacing: TextUnit? = null,
    lineHeight: TextUnit? = null,
    textDecoration: TextDecoration? = null,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = text,
        style = style.copy(
            fontWeight = fontWeight ?: style.fontWeight,
            fontSize = fontSize ?: style.fontSize,
            color = color ?: style.color,
            letterSpacing = letterSpacing ?: style.letterSpacing,
            lineHeight = lineHeight ?: style.lineHeight,
            textDecoration = textDecoration
        ),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
        modifier = modifier.then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    )
}

/**
 * OverlineText - Very small uppercase text, often used for categories or section labels
 */
@Composable
fun BXMasOverlineText(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    fontWeight: FontWeight = FontWeight.Medium,
    fontSize: TextUnit = 12.sp,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    textAlign: TextAlign = TextAlign.Start,
    letterSpacing: TextUnit? = null,
    lineHeight: TextUnit? = null,
    textDecoration: TextDecoration? = null,
    uppercase: Boolean = true,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = if (uppercase) text.uppercase() else text,
        style = style.copy(
            fontWeight = fontWeight,
            fontSize = fontSize,
            color = color,
            letterSpacing = letterSpacing ?: style.letterSpacing,
            lineHeight = lineHeight ?: style.lineHeight,
            textDecoration = textDecoration
        ),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign,
        modifier = modifier.then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    )
}

/**
 * Placeholder Text - Generic subtle text for empty states or hints
 */
@Composable
fun BXMasTextPlaceholder(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 1,
    letterSpacing: TextUnit? = null,
    lineHeight: TextUnit? = null,
    textDecoration: TextDecoration? = null,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = text,
        style = style.copy(
            fontWeight = fontWeight,
            fontSize = fontSize,
            color = color,
            letterSpacing = letterSpacing ?: style.letterSpacing,
            lineHeight = lineHeight ?: style.lineHeight,
            textDecoration = textDecoration
        ),
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    )
}

/**
 * Overline Text - Small uppercase labels, often above titles or sections
 */
@Composable
fun BXMasOverlineText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 12.sp,
    color: Color = MaterialTheme.colorScheme.primary,
    fontWeight: FontWeight = FontWeight.Bold,
    letterSpacing: TextUnit = 1.25.sp,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 1,
    textDecoration: TextDecoration? = null
) {
    Text(
        text = text.uppercase(),
        fontSize = fontSize,
        color = color,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        textDecoration = textDecoration,
        modifier = modifier
    )
}

/**
 * LabelText - Used for small labels, form field titles or key-value pairs
 */
@Composable
fun BXMasLabelText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight? = null,
    fontSize: TextUnit? = null,
    color: Color? = null,
    textAlign: TextAlign = TextAlign.Start,
    letterSpacing: TextUnit? = null,
    lineHeight: TextUnit? = null,
    textDecoration: TextDecoration? = null,
    style: TextStyle = MaterialTheme.typography.labelMedium,
    maxLines: Int = 1,
    onClick: (() -> Unit)? = null
) {
    Text(
        text = text,
        style = style.copy(
            fontWeight = fontWeight ?: style.fontWeight,
            fontSize = fontSize ?: style.fontSize,
            color = color ?: style.color,
            letterSpacing = letterSpacing ?: style.letterSpacing,
            lineHeight = lineHeight ?: style.lineHeight,
            textDecoration = textDecoration
        ),
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.then(
            if (onClick != null) Modifier.clickable { onClick() } else Modifier
        )
    )
}


