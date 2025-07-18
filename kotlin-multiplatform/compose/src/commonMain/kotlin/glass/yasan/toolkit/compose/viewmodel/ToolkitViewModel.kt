package glass.yasan.toolkit.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

public abstract class ToolkitViewModel<
        S : ViewState,
        E : ViewEvent,
        A : ViewAction,
        > : ViewModel() {


    private val _viewState: MutableStateFlow<S> = MutableStateFlow(defaultViewState())
    public val viewState: StateFlow<S> = _viewState.asStateFlow()

    public abstract fun defaultViewState(): S

    public fun updateViewState(body: (state: S) -> S) {
        _viewState.value = body(_viewState.value)
    }

    private val _viewEvent: Channel<E> = Channel(Channel.Factory.BUFFERED)
    private val viewEvent: Flow<E> = _viewEvent.receiveAsFlow()

    private suspend fun collectViewEvents() {
        viewEvent.collect { event ->
            onViewEvent(event)
        }
    }

    @DelicateViewEventApi
    public fun sendViewEvent(event: E) {
        _viewEvent.trySend(event)
    }

    public abstract suspend fun onViewEvent(event: E)

    private val _viewAction: Channel<A> = Channel(Channel.Factory.BUFFERED)
    public val viewAction: Flow<A> = _viewAction.receiveAsFlow()

    public fun sendViewAction(action: A) {
        _viewAction.trySend(action)
    }

    init {
        viewModelScope.launch {
            collectViewEvents()
        }
    }

    public fun <T> Flow<T>.stateIn(initialValue: T): StateFlow<T> =
        stateIn(
            scope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(5.seconds),
            initialValue = initialValue,
        )

}