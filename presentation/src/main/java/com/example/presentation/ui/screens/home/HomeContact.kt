package com.example.presentation.ui.screens.home

import com.example.domain.satellites.model.Satellite

interface HomeContract {
    data class UiState(
        val allSatellites: List<Satellite> = emptyList(),
        val filteredSatellites: List<Satellite> = emptyList(),
        val searchQuery: String = "",
        val isLoading: Boolean = false
    ) {
        val isNoSatellitesFoundForSearchQuery: Boolean
            get() = searchQuery.isNotEmpty() && filteredSatellites.isEmpty() && !isLoading
    }

    sealed class Intent {
        data class Search(val query: String) : Intent()
    }

    sealed class Event
}