package glass.yasan.toolkit.sample.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.toolkit.about.presentation.compose.ToolkitAppBanner
import glass.yasan.toolkit.about.presentation.compose.ToolkitDeveloperBanner
import glass.yasan.toolkit.composeapp.generated.resources.Res
import glass.yasan.toolkit.composeapp.generated.resources.about
import glass.yasan.toolkit.composeapp.generated.resources.app_icon
import glass.yasan.toolkit.composeapp.generated.resources.app_title
import glass.yasan.toolkit.composeapp.generated.resources.decrement
import glass.yasan.toolkit.composeapp.generated.resources.increment
import glass.yasan.toolkit.sample.SampleViewModel
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun HomeScreen(
    viewState: SampleViewModel.State,
    sendViewEvent: (SampleViewModel.Event) -> Unit,
    onNavigateToAbout: () -> Unit,
) {
    val showBuildDetails = remember { mutableStateOf(true) }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            CounterSection(
                count = viewState.count,
                onIncrement = { sendViewEvent(SampleViewModel.Event.Increment) },
                onDecrement = { sendViewEvent(SampleViewModel.Event.Decrement) },
            )
        }

        item { HorizontalDivider() }

        item {
            Button(
                onClick = onNavigateToAbout,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(Res.string.about))
            }
        }

        item { HorizontalDivider() }

        item {
            ToolkitAppBanner(
                appName = stringResource(Res.string.app_title),
                appIcon = painterResource(Res.drawable.app_icon),
                appVersionName = "1.0.0",
                showBuildDetails = showBuildDetails.value,
                buildDetails = persistentListOf(100, "flavor"),
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = null,
                    ) {
                        showBuildDetails.value = !showBuildDetails.value
                    },
            )
        }

        item {
            ToolkitDeveloperBanner(
                isDarkTheme = isSystemInDarkTheme(),
            )
        }
    }
}

@Composable
private fun CounterSection(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = onIncrement,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(Res.string.increment))
        }
        CounterText(count)
        Button(
            onClick = onDecrement,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(Res.string.decrement))
        }
    }
}

@Composable
private fun CounterText(count: Int) {
    AnimatedContent(
        targetState = count,
        transitionSpec = {
            val animationDuration = 200
            if (targetState > initialState) {
                (slideInVertically(animationSpec = tween(animationDuration)) { height -> height } +
                        fadeIn(animationSpec = tween(animationDuration / 2)))
                    .togetherWith(
                        slideOutVertically(animationSpec = tween(animationDuration)) { height -> -height } +
                                fadeOut(animationSpec = tween(animationDuration / 2))
                    )
            } else {
                (slideInVertically(animationSpec = tween(animationDuration)) { height -> -height } +
                        fadeIn(animationSpec = tween(animationDuration / 2)))
                    .togetherWith(
                        slideOutVertically(animationSpec = tween(animationDuration)) { height -> height } +
                                fadeOut(animationSpec = tween(animationDuration / 2))
                    )
            }.using(
                SizeTransform(clip = true)
            )
        }
    ) { count ->
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.displayLarge,
        )
    }
}
