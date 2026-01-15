package com.tech.bxmaschallenge.presentation.ui.photoList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tech.bxmaschallenge.presentation.viewmodel.PhotoSideEffect
import com.tech.bxmaschallenge.presentation.viewmodel.PhotoViewModel
import com.tech.design_system.common.model.BXMasSnackbarMessage
import com.tech.design_system.components.text.BXMasBodyText
import com.tech.domain.model.PhotoUIModel
import org.orbitmvi.orbit.compose.collectAsState


@Composable
fun PhotoListScreen(
    onPhotoClick: (Int) -> Unit,
    showTopSnackbar: (BXMasSnackbarMessage) -> Unit = {},
    showLoader: (Boolean) -> Unit = {},
    viewModel: PhotoViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPhotos()
    }

    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { effect ->
            when (effect) {
                is PhotoSideEffect.ShowError -> {
                    showTopSnackbar(BXMasSnackbarMessage(effect.message))
                }
            }
        }
    }

    when {
        state.isLoading -> showLoader(true)
        state.users.isNotEmpty() -> {
            LazyColumn {
                items(state.users) { photo ->
                    BXMasBodyText(photo.title.orEmpty(),
                        modifier = Modifier.clickable {
                            onPhotoClick(photo.id ?: 0)
                        })
                }
            }
        }
    }

}