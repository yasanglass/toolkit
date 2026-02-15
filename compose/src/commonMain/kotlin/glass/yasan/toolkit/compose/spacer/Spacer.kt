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
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .height(height)
            .width(width),
    )
}

@Composable
public fun VerticalSpacer(
    height: Dp,
    modifier: Modifier = Modifier,
) {
    Spacer(
        height = height,
        width = 0.dp,
        modifier = modifier,
    )
}

@Composable
public fun HorizontalSpacer(
    width: Dp,
    modifier: Modifier = Modifier,
) {
    Spacer(
        height = 0.dp,
        width = width,
        modifier = modifier,
    )
}

public fun LazyListScope.spacerItem(
    height: Dp,
    width: Dp,
    modifier: Modifier = Modifier,
) {
    item {
        Spacer(
            height = height,
            width = width,
            modifier = modifier,
        )
    }
}

public fun LazyListScope.horizontalSpacerItem(
    width: Dp,
    modifier: Modifier = Modifier,
) {
    spacerItem(
        height = 0.dp,
        width = width,
        modifier = modifier,
    )
}

public fun LazyListScope.verticalSpacerItem(
    height: Dp,
    modifier: Modifier = Modifier,
) {
    spacerItem(
        height = height,
        width = 0.dp,
        modifier = modifier,
    )
}
