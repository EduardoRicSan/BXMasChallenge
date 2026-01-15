package com.tech.bxmaschallenge.presentation.navigation.navHost

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tech.bxmaschallenge.presentation.BXMasMainViewModel
import com.tech.bxmaschallenge.presentation.MainIntent
import com.tech.bxmaschallenge.presentation.MainSideEffect
import com.tech.bxmaschallenge.presentation.ui.photoDetail.PhotoDetailScreen
import com.tech.bxmaschallenge.presentation.ui.photoList.PhotoListScreen
import com.tech.core.common.extension.killApp
import com.tech.core.route.PhotoDetail
import com.tech.core.route.PhotoList
import com.tech.design_system.R
import com.tech.design_system.common.model.BXMasDialogMessage
import com.tech.design_system.common.model.asString
import com.tech.design_system.components.dialog.BXMasAlertDialog
import com.tech.design_system.components.scafold.BXMasAppScaffold
import com.tech.design_system.components.topBar.BXMasTopBar

@Composable
fun BXMasNavHost(
    modifier: Modifier = Modifier,
    viewModel: BXMasMainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val state by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    // 🔹 Estado local para visibilidad del dialog
    val showExitDialog = remember { mutableStateOf(false) }

    // 🔹 SideEffects
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.container.sideEffectFlow.collect { effect ->
            when (effect) {
                MainSideEffect.ShowExitDialog -> {
                    showExitDialog.value = true
                }
                MainSideEffect.ExitApp -> {
                    context.killApp()
                }
                else -> Unit
            }
        }
    }

    BXMasAppScaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            BXMasTopBar(
                titleText = state.topBarTitle.asString(),
                currentRoute = state.currentRoute,
                onBackClick = {
                    if (state.currentRoute is PhotoDetail) {
                        navController.popBackStack()
                    }
                },
                onBackLongPress = {
                    if (state.currentRoute is PhotoList) {
                        viewModel.onIntent(MainIntent.BackLongPressed)
                    }
                }
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
                        navController.navigate(PhotoDetail(photoId))
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
    BXMasAlertDialog(
        dialogMessage = BXMasDialogMessage(
            title = stringResource(R.string.title_alert_dialog_exit),
            message = stringResource(R.string.message_alert_dialog_exit),
            confirmLabel = stringResource(R.string.confirm_alert_dialog_exit),
            dismissLabel = stringResource(R.string.dismiss_alert_dialog_exit),
            onConfirm = { viewModel.onIntent(MainIntent.ExitConfirmed) }
        ),
        isVisible = showExitDialog
    )

}