package glass.yasan.spine.compose.foundation

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public val gridUnit: Dp = 8.dp

@Stable
@Deprecated(
    message = "Use dp value instead",
    ReplaceWith("(this * 8).dp", "androidx.compose.ui.unit.dp")
)
public inline val Int.grid: Dp get() = gridUnit * this

@Stable
@Deprecated(
    message = "Use dp value instead",
    ReplaceWith("(this * 8).dp", "androidx.compose.ui.unit.dp")
)
public inline val Float.grid: Dp get() = gridUnit * this
