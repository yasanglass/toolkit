package glass.yasan.toolkit.compose.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
public fun Spacer(
    height: Dp,
    width: Dp,
) {
    Spacer(
        modifier = Modifier
            .height(height)
            .width(width),
    )
}

@Composable
public fun VerticalSpacer(
    height: Dp,
) {
    Spacer(
        height = height,
        width = 0.dp,
    )
}

@Composable
public fun HorizontalSpacer(
    width: Dp,
) {
    Spacer(
        height = 0.dp,
        width = width,
    )
}

public fun LazyListScope.spacerItem(
    height: Dp,
    width: Dp,
) {
    item {
        Spacer(
            height = height,
            width = width,
        )
    }
}

public fun LazyListScope.horizontalSpacerItem(
    width: Dp,
) {
    spacerItem(
        height = 0.dp,
        width = width,
    )
}

public fun LazyListScope.verticalSpacerItem(
    height: Dp,
) {
    spacerItem(
        height = height,
        width = 0.dp,
    )
}
