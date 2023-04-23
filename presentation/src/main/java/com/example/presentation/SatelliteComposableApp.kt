package com.example.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.presentation.ui.composables.SatelliteCircularLoadingDialog
import com.example.presentation.ui.theme.SatelliteTheme
import com.example.presentation.utils.LoadingManager

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
                Text("Hello World")
            }
        )

        if (appState.showLoading) {
            SatelliteCircularLoadingDialog()
        }
    }
}