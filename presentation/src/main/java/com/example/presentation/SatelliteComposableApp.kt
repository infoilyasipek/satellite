package com.example.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.presentation.ui.composables.SatelliteCircularLoadingDialog
import com.example.presentation.ui.screens.NavGraphs
import com.example.presentation.ui.screens.destinations.HomeScreenDestination
import com.example.presentation.ui.theme.SatelliteTheme
import com.example.presentation.utils.LoadingManager
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun SatelliteComposableApp(
    loadingManager: LoadingManager,
) {
    val appState = rememberSatelliteAppState(
        loadingManager = loadingManager,
    )

    SatelliteTheme() {
        Scaffold(
            content = {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    startRoute = HomeScreenDestination,
                    navController = appState.navController,
                    modifier = Modifier.padding(it)
                )
            }
        )

        if (appState.showLoading) {
            SatelliteCircularLoadingDialog()
        }
    }
}