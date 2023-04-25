package com.example.presentation.ui.screens.detail

import com.example.domain.satellites.model.SatelliteDetail
import com.example.domain.satellites.model.SatellitePosition

interface SatelliteDetailContract {
    data class UiState(
        val satelliteDetail: SatelliteDetail? = null,
        val satellitePosition: SatellitePosition? = null
    )

    sealed class Intent {
        object NavigateBack : Intent()
        data class Init(val satelliteId: Int) : Intent()
    }

    sealed class Event {
        object NavigateBack : Event()
    }
}