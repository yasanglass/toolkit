package glass.yasan.toolkit.compose.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

public interface ViewEvent

@OptIn(DelicateViewEventApi::class)
@Composable
public fun <S : ViewState, E : ViewEvent, A : ViewAction> rememberSendViewEvent(
    viewModel: ToolkitViewModel<S, E, A>,
): ((E) -> Unit) {
    val scope = rememberCoroutineScope()

    return remember {
        { event ->
            scope.launch {
                viewModel.sendViewEvent(event)
            }
        }
    }
}

@RequiresOptIn(level = RequiresOptIn.Level.ERROR)
@Retention(AnnotationRetention.BINARY)
public annotation class DelicateViewEventApi
