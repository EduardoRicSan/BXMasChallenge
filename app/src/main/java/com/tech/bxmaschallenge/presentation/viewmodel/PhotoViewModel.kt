package com.tech.bxmaschallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.tech.core.remote.NetworkResult
import com.tech.domain.model.PhotoUIModel
import com.tech.domain.useCase.GetPagedPhotosUseCase
import com.tech.domain.useCase.SyncPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class PhotoState(
    val isLoading: Boolean = false,
    val isPaging: Boolean = false,
    val page: Int = 0,
    val users: List<PhotoUIModel> = emptyList(),
    val endReached: Boolean = false
)

sealed class PhotoSideEffect {
    data class ShowError(val message: String) : PhotoSideEffect()
}


@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val syncPhotosUseCase: SyncPhotosUseCase,
    private val getPagedPhotosUseCase: GetPagedPhotosUseCase
) : ViewModel(), ContainerHost<PhotoState, PhotoSideEffect> {

    override val container =
        container<PhotoState, PhotoSideEffect>(PhotoState())

    private val pageSize = 20

    fun syncPhotos() = intent {
        syncPhotosUseCase().collect { result ->
            when (result) {
                is NetworkResult.Loading -> reduce {
                    state.copy(isLoading = true)
                }

                is NetworkResult.Success -> {
                    reduce { state.copy(isLoading = false) }
                    loadNextPage(reset = true)
                }

                is NetworkResult.Error -> {
                    reduce { state.copy(isLoading = false) }
                    postSideEffect(
                        PhotoSideEffect.ShowError(result.message)
                    )
                }
            }
        }
    }

    fun loadNextPage(reset: Boolean = false) = intent {
        if (state.isPaging || state.endReached) return@intent

        reduce {
            state.copy(
                isPaging = true,
                page = if (reset) 0 else state.page
            )
        }

        val pageToLoad = if (reset) 0 else state.page

        val items = getPagedPhotosUseCase(
            page = pageToLoad,
            pageSize = pageSize
        )

        reduce {
            state.copy(
                users = if (reset) items else state.users + items,
                page = pageToLoad + 1,
                isPaging = false,
                endReached = items.size < pageSize
            )
        }
    }
}

