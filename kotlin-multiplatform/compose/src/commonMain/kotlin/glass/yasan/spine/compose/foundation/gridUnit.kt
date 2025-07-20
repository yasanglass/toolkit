package glass.yasan.spine.compose.foundation

import androidx.annotation.IntRange
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

@Stable
@Deprecated(
    message = "Use dp value instead",
    ReplaceWith("(multiplier * 8).dp", "androidx.compose.ui.unit.dp")
)
public fun grid(multiplier: Float): Dp = gridUnit * multiplier

@Stable
@Deprecated(
    message = "Use dp value instead",
    ReplaceWith("(multiplier * 8).dp", "androidx.compose.ui.unit.dp")
)
public fun grid(@IntRange(from = 2) multiplier: Int): Dp = gridUnit * multiplier
