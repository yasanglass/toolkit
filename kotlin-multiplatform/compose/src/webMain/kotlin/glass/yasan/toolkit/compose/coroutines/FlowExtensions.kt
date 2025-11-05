package glass.yasan.toolkit.compose.coroutines

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
public actual fun <T> Flow<T>.collectAsStateWithLifecycleIfAvailable(
    initialValue: T,
): State<T> = collectAsState(
    initial = initialValue,
)

@Composable
public actual fun <T> StateFlow<T>.collectAsStateWithLifecycleIfAvailable(): State<T> = collectAsState()
