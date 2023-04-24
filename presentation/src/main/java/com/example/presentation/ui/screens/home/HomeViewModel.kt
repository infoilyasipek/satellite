package com.example.presentation.ui.screens.home

import com.example.domain.satellites.model.usecase.GetSatellitesUseCase
import com.example.domain.satellites.model.usecase.SearchSatellitesUseCase
import com.example.presentation.ui.base.BaseViewModel
import com.example.presentation.ui.screens.home.HomeContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSatellitesUseCase: GetSatellitesUseCase,
    private val searchSatellitesUseCase: SearchSatellitesUseCase
) : BaseViewModel<UiState, Intent, Event>(
    initState = UiState()
) {

    private var searchJob: Job? = null

    override fun onFirstLaunch() {
        super.onFirstLaunch()

        withUseCaseScope(
            loadingUpdater = ::updateLoadingState
        ) {
            val satellites = getSatellitesUseCase()
            updateUiState { copy(allSatellites = satellites, filteredSatellites = satellites) }
        }
    }

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.Search -> {
                search(searchQuery = intent.query)
            }
        }
    }

    private fun search(searchQuery: String) {
        updateUiState {
            copy(
                searchQuery = searchQuery,
                isLoading = true,
                filteredSatellites = emptyList()
            )
        }
        searchJob?.cancel()
        searchJob = withUseCaseScope(
            loadingUpdater = ::updateLoadingState
        ) {
            delay(200)
            val searchResult = searchSatellitesUseCase(
                allSatellites = uiState.value.allSatellites,
                query = searchQuery
            )
            updateUiState {
                copy(
                    filteredSatellites = searchResult,
                    isLoading = false
                )
            }
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        updateUiState { copy(isLoading = isLoading) }
    }
}