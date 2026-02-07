package glass.yasan.toolkit.compose.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable

/**
 * A custom [AnimatedVisibility] that uses fade in/out and vertical expand/shrink animations.
 */
@Composable
public fun VerticalAnimatedVisibility(
    visible: Boolean,
    content: @Composable() AnimatedVisibilityScope.() -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
        content = content,
    )
}
