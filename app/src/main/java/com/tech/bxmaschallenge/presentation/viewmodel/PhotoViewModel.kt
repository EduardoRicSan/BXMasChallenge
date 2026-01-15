package com.tech.bxmaschallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.core.remote.NetworkResult
import com.tech.domain.model.PhotoUIModel
import com.tech.domain.useCase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

data class PhotoState(
    val isLoading: Boolean = false,
    val users: List<PhotoUIModel> = emptyList(),
)

sealed class PhotoSideEffect {
    data class ShowError(val message: String) : PhotoSideEffect()
}


@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel(), ContainerHost<PhotoState, PhotoSideEffect> {

    override val container =
        container<PhotoState, PhotoSideEffect>(PhotoState())

    fun loadPhotos() = intent {
        if (state.isLoading) return@intent
        getPhotosUseCase().collect { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    reduce {
                        state.copy(isLoading = true)
                    }
                }
                is NetworkResult.Success -> {
                    reduce {
                        state.copy(
                            isLoading = false,
                            users = result.data
                        )
                    }
                }
                is NetworkResult.Error -> {
                    reduce {
                        state.copy(isLoading = false)
                    }
                    postSideEffect(
                        PhotoSideEffect.ShowError(result.message)
                    )
                }
            }
        }
    }
}
