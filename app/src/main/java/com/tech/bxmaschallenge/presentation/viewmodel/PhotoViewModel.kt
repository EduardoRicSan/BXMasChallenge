package com.tech.bxmaschallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.tech.core.remote.NetworkResult
import com.tech.domain.model.PhotoUIModel
import com.tech.domain.useCase.EnsurePhotosSyncedUseCase
import com.tech.domain.useCase.GetPagedPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

enum class PhotoLoadPhase {
    IDLE,
    SYNCING,
    LOADING_PAGE
}

data class PhotoState(
    val phase: PhotoLoadPhase = PhotoLoadPhase.IDLE,
    val page: Int = 0,
    val users: List<PhotoUIModel> = emptyList(),
    val endReached: Boolean = false
)

sealed class PhotoSideEffect {
    data class ShowError(val message: String) : PhotoSideEffect()
}


@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val ensurePhotosSyncedUseCase: EnsurePhotosSyncedUseCase,
    private val getPagedPhotosUseCase: GetPagedPhotosUseCase
) : ViewModel(),
    ContainerHost<PhotoState, PhotoSideEffect> {

    override val container =
        container<PhotoState, PhotoSideEffect>(PhotoState())

    private val pageSize = 50

    /* ------------------------------------ */
    /* Sync inicial + primera página         */
    /* ------------------------------------ */

    fun ensurePhotosSynced() = intent {
        if (state.phase != PhotoLoadPhase.IDLE) return@intent

        reduce {
            state.copy(
                phase = PhotoLoadPhase.SYNCING,
                page = 0,
                endReached = false,
                users = emptyList()
            )
        }

        ensurePhotosSyncedUseCase().collect { result ->
            when (result) {
                is NetworkResult.Loading -> Unit

                is NetworkResult.Error -> {
                    reduce { state.copy(phase = PhotoLoadPhase.IDLE) }
                    postSideEffect(
                        PhotoSideEffect.ShowError(result.message)
                    )
                }

                is NetworkResult.Success -> {
                    // 👉 Primera página DENTRO del intent
                    reduce { state.copy(phase = PhotoLoadPhase.LOADING_PAGE) }

                    delay(600) // simulación UX visible

                    val items = getPagedPhotosUseCase(
                        page = 0,
                        pageSize = pageSize
                    )

                    reduce {
                        state.copy(
                            users = items,
                            page = 1,
                            endReached = items.size < pageSize,
                            phase = PhotoLoadPhase.IDLE
                        )
                    }
                }
            }
        }
    }

    /* ------------------------------------ */
    /* Paginación                            */
    /* ------------------------------------ */

    fun loadNextPage() = intent {
        if (
            state.phase != PhotoLoadPhase.IDLE ||
            state.endReached
        ) return@intent

        reduce {
            state.copy(phase = PhotoLoadPhase.LOADING_PAGE)
        }

        delay(400)

        val currentPage = state.page

        val items = getPagedPhotosUseCase(
            page = currentPage,
            pageSize = pageSize
        )

        reduce {
            state.copy(
                users = state.users + items,
                page = currentPage + 1,
                endReached = items.size < pageSize,
                phase = PhotoLoadPhase.IDLE
            )
        }
    }
}



