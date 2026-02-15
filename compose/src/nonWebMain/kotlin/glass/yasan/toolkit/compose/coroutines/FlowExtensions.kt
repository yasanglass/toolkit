package glass.yasan.toolkit.compose.coroutines

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import glass.yasan.toolkit.core.coroutines.firstBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
public fun <T> Flow<T>.collectAsStateWithLifecycleBlocking(): State<T> = collectAsStateWithLifecycle(
    initialValue = firstBlocking(),
)
