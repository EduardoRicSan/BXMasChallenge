package com.tech.bxmaschallenge.presentation.ui.photoList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tech.bxmaschallenge.presentation.viewmodel.PhotoSideEffect
import com.tech.bxmaschallenge.presentation.viewmodel.PhotoViewModel
import com.tech.design_system.common.model.BXMasSnackbarMessage
import com.tech.design_system.components.text.BXMasBodyText
import org.orbitmvi.orbit.compose.collectAsState


@Composable
fun PhotoListScreen(
    onPhotoClick: (Int) -> Unit,
    showTopSnackbar: (BXMasSnackbarMessage) -> Unit = {},
    showLoader: (Boolean) -> Unit = {},
    viewModel: PhotoViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    val listState = rememberLazyListState()

    // 🔹 Carga inicial (sync remoto → local)
    LaunchedEffect(Unit) {
        viewModel.syncPhotos()
    }

    // 🔹 Side effects
    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { effect ->
            when (effect) {
                is PhotoSideEffect.ShowError -> {
                    showTopSnackbar(
                        BXMasSnackbarMessage(effect.message)
                    )
                }
            }
        }
    }

    // 🔹 Scroll pagination
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleIndex ->
            if (
                lastVisibleIndex == state.users.lastIndex &&
                !state.isPaging &&
                !state.endReached
            ) {
                viewModel.loadNextPage()
            }
        }
    }

    showLoader(state.isLoading)

    LazyColumn(
        state = listState
    ) {
        items(state.users) { photo ->
            BXMasBodyText(
                photo.title.orEmpty(),
                modifier = Modifier.clickable {
                    onPhotoClick(photo.id ?: 0)
                }
            )
        }

        item {
            if (state.isPaging) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
