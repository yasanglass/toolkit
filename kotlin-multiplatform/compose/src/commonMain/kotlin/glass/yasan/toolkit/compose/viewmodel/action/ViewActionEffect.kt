package glass.yasan.toolkit.compose.viewmodel.action

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

@Composable
public fun <A : ViewAction> ViewActionEffect(
    viewAction: Flow<A>,
    onViewAction: (A) -> Unit,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(viewAction, lifecycle) {
        viewAction
            .flowWithLifecycle(lifecycle)
            .flowOn(Dispatchers.Main)
            .collect(onViewAction)
    }
}
