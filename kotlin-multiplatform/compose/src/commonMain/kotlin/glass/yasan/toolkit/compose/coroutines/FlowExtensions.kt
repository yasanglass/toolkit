package glass.yasan.toolkit.compose.coroutines

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import glass.yasan.toolkit.core.coroutines.firstBlocking
import kotlinx.coroutines.flow.Flow
import androidx.compose.runtime.State

@Composable
public fun <T> Flow<T>.collectAsStateWithLifecycleBlocking(): State<T> =
    collectAsStateWithLifecycle(initialValue = firstBlocking())
