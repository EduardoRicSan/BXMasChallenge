package com.tech.bxmaschallenge.presentation.navigation.navHost

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tech.bxmaschallenge.presentation.BXMasMainViewModel
import com.tech.bxmaschallenge.presentation.MainIntent
import com.tech.bxmaschallenge.presentation.navigation.route.PhotoDetail
import com.tech.bxmaschallenge.presentation.navigation.route.PhotoList
import com.tech.bxmaschallenge.presentation.ui.photoDetail.PhotoDetailScreen
import com.tech.bxmaschallenge.presentation.ui.photoList.PhotoListScreen
import com.tech.design_system.common.model.asString
import com.tech.design_system.components.scafold.BXMasAppScaffold
import com.tech.design_system.components.topBar.BXMasTopBar

@Composable
fun BXMasNavHost(
    modifier: Modifier = Modifier,
    viewModel: BXMasMainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    BXMasAppScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            BXMasTopBar(
                titleText = state.topBarTitle.asString(),
                showBackButton = state.currentRoute !is PhotoList,
                onBackClick = { navController.popBackStack() }
            )
        },
    ) { paddingValues, showTopSnackbar, triggerLoader ->
        NavHost(
            navController = navController,
            startDestination = PhotoList,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable<PhotoList> {
                LaunchedEffect(Unit) {
                    viewModel.onIntent(
                        MainIntent.RouteChanged(PhotoList)
                    )
                }
                PhotoListScreen(
                    showTopSnackbar = showTopSnackbar,
                    showLoader = triggerLoader,
                    onPhotoClick = { photoId ->
                        navController.navigate(
                            PhotoDetail(photoId)
                        )
                    }
                )
            }

            composable<PhotoDetail> { backStackEntry ->
                val route = backStackEntry.toRoute<PhotoDetail>()
                LaunchedEffect(Unit) {
                    viewModel.onIntent(
                        MainIntent.RouteChanged(PhotoDetail(route.photoId))
                    )
                }
                PhotoDetailScreen(
                    photoId = route.photoId
                )
            }
        }
    }

}