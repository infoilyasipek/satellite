package com.example.presentation.ui.screens.detail

import androidx.lifecycle.viewModelScope
import com.example.domain.satellites.model.SatellitePosition
import com.example.domain.satellites.usecase.GetSatelliteDetailUseCase
import com.example.domain.satellites.usecase.GetSatellitePositionsUseCase
import com.example.presentation.ui.base.BaseViewModel
import com.example.presentation.ui.screens.detail.SatelliteDetailContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val getSatellitePositionsUseCase: GetSatellitePositionsUseCase,
    private val getSatelliteDetailUseCase: GetSatelliteDetailUseCase
) : BaseViewModel<UiState, Intent, Event>(
    initState = UiState()
) {
    var satellitePositions: List<SatellitePosition> = emptyList()

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.Init -> {
                if (isInitialized) return

                withUseCaseScope {
                    val satelliteDetail = getSatelliteDetailUseCase(intent.satelliteId)
                    satellitePositions = getSatellitePositionsUseCase(intent.satelliteId)

                    updateUiState {
                        copy(
                            satelliteDetail = satelliteDetail,
                            satellitePosition = satellitePositions.random()
                        )
                    }

                    startPositionUpdatingTimer()
                }
                initialize()
            }
            Intent.NavigateBack -> triggerEvent(Event.NavigateBack)
        }
    }

    private fun startPositionUpdatingTimer() {
        viewModelScope.launch {
            fixedRateTimer(
                name = "timer",
                daemon = false,
                initialDelay = 0,
                period = UPDATE_POSITION_DURATION
            ) {
                updateUiState {
                    copy(
                        satellitePosition = satellitePositions.random()
                    )
                }
            }
        }
    }

    companion object {
        private const val UPDATE_POSITION_DURATION = 3000L
    }
}