package com.tech.bxmaschallenge.presentation

import androidx.lifecycle.ViewModel
import com.tech.bxmaschallenge.presentation.navigation.extension.toTopBarTitle
import com.tech.core.route.BXMasAppDestination
import com.tech.core.route.PhotoList
import com.tech.design_system.common.model.UiText
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container


data class MainState(
    val currentRoute: BXMasAppDestination = PhotoList,
    val topBarTitle: UiText = UiText.Dynamic(""),
    val showBottomBar: Boolean = false
)

sealed class MainIntent {
    data class RouteChanged(val route: BXMasAppDestination?) : MainIntent()
    data object BackLongPressed : MainIntent()
    data object ExitConfirmed : MainIntent()
}

sealed interface MainSideEffect {
    data class ShowMessage(
        val message: String
    ) : MainSideEffect
    data object ShowExitDialog : MainSideEffect
    data object ExitApp : MainSideEffect
}

class BXMasMainViewModel : ViewModel(),
    ContainerHost<MainState, MainSideEffect> {

    override val container = container<MainState, MainSideEffect>(
        MainState()
    )

    fun onIntent(intent: MainIntent) = intent {
        when (intent) {

            is MainIntent.RouteChanged -> {
                val safeRoute = intent.route ?: PhotoList
                reduce {
                    state.copy(
                        currentRoute = safeRoute,
                        topBarTitle = safeRoute.toTopBarTitle()
                    )
                }
            }

            MainIntent.BackLongPressed -> {
                if (state.currentRoute is PhotoList) {
                    postSideEffect(MainSideEffect.ShowExitDialog)
                }
            }

            MainIntent.ExitConfirmed -> {
                postSideEffect(MainSideEffect.ExitApp)
            }
        }
    }
}
