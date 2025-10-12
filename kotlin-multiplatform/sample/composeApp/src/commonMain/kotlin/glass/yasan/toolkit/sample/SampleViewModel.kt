package glass.yasan.toolkit.sample

import glass.yasan.toolkit.compose.viewmodel.ToolkitViewModel
import glass.yasan.toolkit.compose.viewmodel.ViewAction
import glass.yasan.toolkit.compose.viewmodel.ViewEvent
import glass.yasan.toolkit.compose.viewmodel.ViewState
import glass.yasan.toolkit.sample.SampleViewModel.Action
import glass.yasan.toolkit.sample.SampleViewModel.Event
import glass.yasan.toolkit.sample.SampleViewModel.State

internal class SampleViewModel : ToolkitViewModel<State, Event, Action>() {

    override fun defaultViewState(): State = State()

    data class State(
        val count: Int = 0,
    ) : ViewState

    sealed interface Event : ViewEvent {
        data object Increment : Event
        data object Decrement : Event
    }

    sealed interface Action : ViewAction

    override suspend fun onViewEvent(event: Event) {
        when (event) {
            Event.Increment -> {
                updateViewState { copy(count = count + 1) }
            }

            Event.Decrement -> {
                updateViewState { copy(count = count - 1) }
            }
        }
    }
}
