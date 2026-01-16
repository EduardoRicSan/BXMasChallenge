package com.tech.bxmaschallenge.presentation.ui.photoList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tech.design_system.common.model.BXMasImageSource
import com.tech.design_system.components.image.BXMasCircularImage
import com.tech.design_system.components.text.BXMasLabelText
import com.tech.domain.model.PhotoUIModel

// Composable that displays a photo item with title and circular image, clickable to trigger an action
@Composable
fun PhotoItemComposable(
    photo: PhotoUIModel,
    onPhotoClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPhotoClick(photo.id ?: 0) }
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BXMasLabelText(
                photo.title.orEmpty(),
                maxLines = Int.MAX_VALUE,
                overflow = TextOverflow.Visible,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp)
            )

            BXMasCircularImage(
                source = BXMasImageSource.Url(photo.url ?: photo.thumbnailUrl ?: ""),
                size = 80.dp,
                borderWidth = 2.dp,
                borderColor = MaterialTheme.colorScheme.primary
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.onBackground)
        )
    }
}

// Preview for the PhotoItemComposable
@Preview(showBackground = true)
@Composable
fun PreviewPhotoItemComposable() {
    val samplePhoto = PhotoUIModel(
        id = 1,
        title = "Sample Photo Title",
        url = "https://via.placeholder.com/150",
        thumbnailUrl = "https://via.placeholder.com/100"
    )

    PhotoItemComposable(
        photo = samplePhoto,
        onPhotoClick = { photoId ->
            println("Clicked photo id: $photoId")
        }
    )
}

