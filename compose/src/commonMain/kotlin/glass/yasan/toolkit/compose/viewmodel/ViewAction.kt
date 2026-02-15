package glass.yasan.toolkit.compose.viewmodel;

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import glass.yasan.toolkit.core.coroutines.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.compose.koinInject

public interface ViewAction

@Composable
public fun <A : ViewAction> ViewActionEffect(
    viewAction: Flow<A>,
    onViewAction: (A) -> Unit,
) {
    val dispatcherProvider: DispatcherProvider = koinInject()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(viewAction, lifecycle) {
        viewAction
            .flowWithLifecycle(lifecycle)
            .flowOn(dispatcherProvider.main)
            .collect(onViewAction)
    }
}
