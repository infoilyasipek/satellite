package com.example.presentation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.utils.LoadingManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberSatelliteAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    loadingManager: LoadingManager
) = remember(
    coroutineScope,
    navController,
    loadingManager
) {
    SatelliteAppState(
        coroutineScope,
        navController,
        loadingManager,
    )
}

@Stable
class SatelliteAppState(
    coroutineScope: CoroutineScope,
    val navController: NavHostController,
    private val loadingManager: LoadingManager,
) {
    init {
        coroutineScope.launch {
            loadingManager.loading.collect {
                _showLoading = it
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Loading state
    ///////////////////////////////////////////////////////////////////////////
    private var _showLoading by mutableStateOf(false)
    val showLoading: Boolean
        @Composable get() = _showLoading
}