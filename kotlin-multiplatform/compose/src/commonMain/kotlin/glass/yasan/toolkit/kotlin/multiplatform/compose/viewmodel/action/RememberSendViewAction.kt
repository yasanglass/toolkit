package glass.yasan.toolkit.kotlin.multiplatform.compose.viewmodel.action

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import glass.yasan.toolkit.kotlin.multiplatform.compose.viewmodel.ToolkitViewModel
import glass.yasan.toolkit.kotlin.multiplatform.compose.viewmodel.event.DelicateViewEventApi
import glass.yasan.toolkit.kotlin.multiplatform.compose.viewmodel.event.ViewEvent
import glass.yasan.toolkit.kotlin.multiplatform.compose.viewmodel.state.ViewState
import kotlinx.coroutines.launch

@OptIn(DelicateViewEventApi::class)
@Composable
fun <S : ViewState, U : ViewEvent, D : ViewAction> rememberSendViewEvent(
    viewModel: ToolkitViewModel<S, U, D>,
): ((U) -> Unit) {
    val scope = rememberCoroutineScope()

    return remember {
        { event ->
            scope.launch {
                viewModel.sendViewEvent(event)
            }
        }
    }
}
