package glass.yasan.toolkit.compose.coroutines

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Internally either uses `collectAsStateWithLifecycle` or `collectAsState()`
 * depending on the availability of `collectAsStateWithLifecycle` on the target platform.
 */
@Composable
public expect fun <T> Flow<T>.collectAsStateWithLifecycleIfAvailable(
    initialValue: T,
): State<T>

/**
 * Internally either uses `collectAsStateWithLifecycle` or `collectAsState()`
 * depending on the availability of `collectAsStateWithLifecycle` on the target platform.
 */
@Composable
public expect fun <T> StateFlow<T>.collectAsStateWithLifecycleIfAvailable(): State<T>
