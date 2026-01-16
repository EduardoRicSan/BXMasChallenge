package com.tech.bxmaschallenge.presentation.ui.photoDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tech.bxmaschallenge.presentation.viewmodel.PhotoDetailIntent
import com.tech.bxmaschallenge.presentation.viewmodel.PhotoDetailSideEffect
import com.tech.bxmaschallenge.presentation.viewmodel.PhotoDetailViewModel
import com.tech.design_system.R
import com.tech.design_system.common.model.BXMasImageSource
import com.tech.design_system.common.model.BXMasSnackbarMessage
import com.tech.design_system.components.image.BXMasCircularImage
import com.tech.design_system.components.image.BXMasImage
import com.tech.design_system.components.text.BXMasBodyText
import com.tech.design_system.components.text.BXMasHeadlineText
import com.tech.design_system.components.text.BXMasSubtitleText
import com.tech.domain.model.PhotoUIModel

const val MIKY_IMAGE = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSluCALvAreaSfMGNRzPK72hJjpqsRTla-Vow&s"
const val PERSONAL_DUMMY_IMAGE = "https://media.licdn.com/dms/image/v2/C4E03AQGrahdPFsBXRA/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1629826477560?e=2147483647&v=beta&t=L_fWqCd9kEH3qM5tcuUZqAybyANPsM5fysqbUwNNIfw"

// Composable that displays the detail of a photo, handles loading state and error side effects
@Composable
fun PhotoDetailScreen(
    photoId: Int,
    showTopSnackbar: (BXMasSnackbarMessage) -> Unit = {},
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { effect ->
            when (effect) {
                is PhotoDetailSideEffect.ShowError -> {
                    showTopSnackbar(
                        BXMasSnackbarMessage(effect.message)
                    )
                }
            }
        }
    }

    LaunchedEffect(photoId) {
        viewModel.onIntent(PhotoDetailIntent.LoadPhoto(photoId))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            DetailPhotoContent(state.photo)
        }
    }
}

// Composable that shows the photo content and developer info, including images and text
@Composable
fun DetailPhotoContent(photo: PhotoUIModel?) {
    photo?.let { photo ->
        BXMasImage(
            modifier = Modifier.fillMaxWidth().height(300.dp),
            source = BXMasImageSource.Url(photo.url ?: photo.thumbnailUrl ?: ""),
        )
        BXMasBodyText(
            text = photo.title.orEmpty(), modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

    } ?: run {
        BXMasBodyText(text = stringResource(R.string.error_no_photo_found))
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        BXMasCircularImage(
            source = BXMasImageSource.Url(PERSONAL_DUMMY_IMAGE),
            size = 120.dp,
        )
        BXMasCircularImage(
            source = BXMasImageSource.Url(MIKY_IMAGE),
            size = 120.dp,
        )
    }
    BXMasHeadlineText(
        text = stringResource(R.string.about_dev_name),
        textAlign = TextAlign.Center
    )
    BXMasSubtitleText(
        text = stringResource(R.string.about_dev_role),
    )
    BXMasBodyText(
        text = stringResource(R.string.about_dev_about),
        textAlign = TextAlign.Center
    )
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.colorScheme.onBackground)
    )
    BXMasBodyText(
        text = stringResource(R.string.about_favorite_book),
        textAlign = TextAlign.Center
    )
    BXMasBodyText(
        text = stringResource(R.string.about_favorite_character),
        textAlign = TextAlign.Center
    )
    BXMasBodyText(
        text = stringResource(R.string.about_ninja_turtle),
        textAlign = TextAlign.Center
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewDetailPhotoContent_WithPhoto() {
    val samplePhoto = PhotoUIModel(
        id = 1,
        title = "Sample Photo Title",
        url = "https://via.placeholder.com/300",
        thumbnailUrl = "https://via.placeholder.com/150"
    )

    DetailPhotoContent(photo = samplePhoto)
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailPhotoContent_NoPhoto() {
    DetailPhotoContent(photo = null) // Preview when no photo is available
}


