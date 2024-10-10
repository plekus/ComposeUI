package ru.example.composeui.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Event, Action>(initState: State) : ViewModel() {

    private val _uiState = MutableStateFlow(initState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()
    private val _uiEvent = MutableSharedFlow<Event?>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val uiEvent: SharedFlow<Event?> = _uiEvent.asSharedFlow()

    abstract fun obtainAction(action: Action)

    fun updateState(block: State.() -> State) {
        _uiState.value = block(_uiState.value)
    }

    fun postEvent(event: Event) {
        _uiEvent.tryEmit(event)
        _uiEvent.resetReplayCache()
    }

    /**
     * Convenient method to perform work in [viewModelScope] scope.
     */
    protected fun withViewModelScope(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(block = block)
    }
}