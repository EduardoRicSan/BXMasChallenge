package com.tech.bxmaschallenge.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tech.bxmaschallenge.presentation.navigation.extension.toTopBarTitle
import com.tech.bxmaschallenge.presentation.navigation.route.BXMasAppDestination
import com.tech.bxmaschallenge.presentation.navigation.route.PhotoList
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
}

sealed interface MainSideEffect {
    data class ShowMessage(
        val message: String
    ) : MainSideEffect
}

class BXMasMainViewModel() : ViewModel(), ContainerHost<MainState, MainSideEffect> {

    override val container = container<MainState, MainSideEffect>(
        MainState()
    )

    fun onIntent(intent: MainIntent) = intent {
        when (intent) {
            is MainIntent.RouteChanged -> {

                val safeRoute = intent.route ?: PhotoList

                reduce {
                    Log.d("NAV", "Route changed to: $safeRoute")
                    state.copy(
                        currentRoute = safeRoute,
                        topBarTitle = safeRoute.toTopBarTitle(),
                    )
                }
            }
        }
    }
}