package com.example.presentation.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation.utils.LoadingManager
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
inline fun <reified VM : BaseViewModel<*, *, *>> satelliteViewModel(
    initByDefault: Boolean = true
): VM = hiltViewModel<VM>().also {
    if (initByDefault) it.initialize()
}

abstract class BaseViewModel<UiState, Intent, Event>(initState: UiState) : ViewModel(),
    CoroutinesUseCaseRunner {

    @Inject
    lateinit var loadingManager: LoadingManager

    private val _events: Channel<Event> = Channel()
    val events: Flow<Event> = _events.receiveAsFlow()

    private val _uiState = MutableStateFlow(initState)
    val uiState = _uiState.asStateFlow()

    val currentUiState: UiState
        get() = _uiState.value

    /**
     * This indicates that the screen/dialog etc is successfully loaded the initial data
     * that is required at the first launch of it
     * */
    protected var isInitialized: Boolean = false

    // This method will be called after injecting the vm
    open fun initialize() {
        if (isInitialized.not()) {
            onFirstLaunch()
            isInitialized = true
        }
    }

    // This method will be used to load data after injecting the vm
    protected open fun onFirstLaunch() {}

    private var loadingCount: Int = 0
        set(value) {
            loadingManager.setLoading(value > 0)
            field = value
        }

    protected fun updateUiState(reducer: UiState.() -> UiState) {
        val newState = uiState.value.reducer()
        _uiState.value = newState
    }

    abstract fun onIntent(intent: Intent)

    protected fun triggerEvent(event: Event) {
        viewModelScope.launch { _events.send(event) }
    }

    override val useCaseScope = viewModelScope

    override fun withUseCaseScope(
        loadingUpdater: ((Boolean) -> Unit)?,
        onError: ((Throwable) -> Unit)?,
        onComplete: (() -> Unit)?,
        block: suspend () -> Unit
    ): Job {
        val defaultLoadingUpdater = { isLoading: Boolean ->
            if (isLoading) {
                loadingCount++
            } else {
                loadingCount--
            }
        }
        return super.withUseCaseScope(
            loadingUpdater = {
                loadingUpdater?.invoke(it) ?: defaultLoadingUpdater(it)
            },
            onError = {
                if (it is CancellationException) {
                    return@withUseCaseScope
                }
                onError?.invoke(it) ?: handleError(it)
            },
            onComplete = onComplete,
            block = block
        )
    }

    private fun handleError(throwable: Throwable) {
        // sorry guys i run out of time to implement this :)
        // but it would be exactly the same as the loading manager
        // and instead of showing a loading it would show an error dialog :)
    }

    fun updateLoading(isLoading: Boolean) {
        if (isLoading) {
            loadingCount++
        } else {
            loadingCount--
        }
    }

    @Composable
    operator fun component1(): UiState {
        return uiState.collectAsState().value
    }

    operator fun component2(): (Intent) -> Unit = ::onIntent

    operator fun component3(): Flow<Event> = events
}
