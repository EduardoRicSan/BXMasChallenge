package com.tech.bxmaschallenge.presentation.ui.photoDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tech.design_system.components.text.BXMasBodyText

@Composable
fun PhotoDetailScreen(photoId: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),

    ) {
        BXMasBodyText(text = "Photo Detail ID = $photoId")
    }
}