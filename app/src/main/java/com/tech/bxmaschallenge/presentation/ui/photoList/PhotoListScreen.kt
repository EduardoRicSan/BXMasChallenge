package com.tech.bxmaschallenge.presentation.ui.photoList

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.tech.bxmaschallenge.presentation.viewmodel.PhotoViewModel
import com.tech.domain.model.PhotoUIModel

@Composable
fun PhotoListScreen(
    onPhotoClick: (PhotoUIModel) -> Unit,
    viewModel: PhotoViewModel = hiltViewModel()
) {

}