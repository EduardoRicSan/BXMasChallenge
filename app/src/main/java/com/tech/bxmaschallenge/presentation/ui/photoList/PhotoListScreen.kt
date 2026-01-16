package com.tech.bxmaschallenge.presentation.ui.photoList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tech.bxmaschallenge.presentation.viewmodel.PhotoLoadPhase
import com.tech.bxmaschallenge.presentation.viewmodel.PhotoSideEffect
import com.tech.bxmaschallenge.presentation.viewmodel.PhotoViewModel
import com.tech.design_system.common.model.BXMasSnackbarMessage
import org.orbitmvi.orbit.compose.collectAsState


// Composable that displays a photo list item  title and circular image
@Composable
fun PhotoListScreen(
    onPhotoClick: (Int) -> Unit,
    showTopSnackbar: (BXMasSnackbarMessage) -> Unit = {},
    showLoader: (Boolean) -> Unit = {},
    viewModel: PhotoViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    val listState = rememberLazyListState()

    /* ------------------------------------ */
    /* Initial sync                          */
    /* ------------------------------------ */
    LaunchedEffect(Unit) {
        viewModel.ensurePhotosSynced()
    }

    /* ------------------------------------ */
    /* Side effects                          */
    /* ------------------------------------ */
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

    /* ------------------------------------ */
    /* Pagination trigger                    */
    /* ------------------------------------ */
    LaunchedEffect(listState) {
        snapshotFlow {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }.collect { lastVisibleIndex ->
            val shouldLoadNext =
                lastVisibleIndex == state.users.lastIndex &&
                        state.phase == PhotoLoadPhase.IDLE &&
                        !state.endReached

            if (shouldLoadNext) {
                viewModel.loadNextPage()
            }
        }
    }

    /* ------------------------------------ */
    /* Global loader (sync)                  */
    /* ------------------------------------ */
    showLoader(state.phase == PhotoLoadPhase.SYNCING)

    /* ------------------------------------ */
    /* List                                  */
    /* ------------------------------------ */
    LazyColumn(
        state = listState
    ) {
        items(
            items = state.users,
            key = { it.id ?: it.hashCode() } // 👈 importante para performance
        ) { photo ->
            PhotoItemComposable(
                photo = photo,
                onPhotoClick = onPhotoClick
            )
        }

        /* ------------------------------------ */
        /* Paging footer loader                 */
        /* ------------------------------------ */
        if (state.phase == PhotoLoadPhase.LOADING_PAGE &&
            state.users.isNotEmpty()
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

