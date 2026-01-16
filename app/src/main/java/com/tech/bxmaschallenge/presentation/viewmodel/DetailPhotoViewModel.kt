package com.tech.bxmaschallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.tech.domain.model.PhotoUIModel
import com.tech.domain.useCase.GetPhotoByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

// ----------------------
// State
// ----------------------
data class PhotoDetailState(
    val photo: PhotoUIModel? = null,
    val isLoading: Boolean = false
)

// ----------------------
// Intent
// ----------------------
sealed class PhotoDetailIntent {
    data class LoadPhoto(val photoId: Int) : PhotoDetailIntent()
}

// ----------------------
// SideEffect
// ----------------------
sealed interface PhotoDetailSideEffect {
    data class ShowError(val message: String) : PhotoDetailSideEffect
}

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoByIdUseCase: GetPhotoByIdUseCase
) : ViewModel(),
    ContainerHost<PhotoDetailState, PhotoDetailSideEffect> {

    override val container = container<PhotoDetailState, PhotoDetailSideEffect>(
        initialState = PhotoDetailState()
    )

    fun onIntent(intent: PhotoDetailIntent) = intent {
        when (intent) {
            is PhotoDetailIntent.LoadPhoto -> loadPhoto(intent.photoId)
        }
    }

    private fun loadPhoto(photoId: Int) = intent {
        // Indicar que estamos cargando
        reduce { state.copy(isLoading = true) }

        getPhotoByIdUseCase(photoId)
            .catch { e ->
                postSideEffect(PhotoDetailSideEffect.ShowError(e.message ?: "Error desconocido"))
            }
            .collect { photo ->
                reduce { state.copy(photo = photo, isLoading = false) }
            }
    }
}
