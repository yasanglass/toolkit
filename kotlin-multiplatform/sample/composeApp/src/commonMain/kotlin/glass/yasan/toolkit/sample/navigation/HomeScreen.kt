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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import glass.yasan.concrete.component.HorizontalDivider
import glass.yasan.concrete.component.Switch
import glass.yasan.concrete.component.Text
import glass.yasan.concrete.foundation.theme.ConcreteTheme
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
    isDarkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    viewState: SampleViewModel.State,
    sendViewEvent: (SampleViewModel.Event) -> Unit,
    onNavigateToAbout: () -> Unit,
) {
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
                Text(
                    text = stringResource(Res.string.about),
                    color = ConcreteTheme.colors.onPrimary,
                )
            }
        }

        item { HorizontalDivider() }

        item {
            DarkThemeSwitch(
                checked = isDarkTheme,
                onCheckedChange = onDarkThemeChange,
            )
        }

        item { HorizontalDivider() }

        item {
            ToolkitAppBanner(
                appName = stringResource(Res.string.app_title),
                appIcon = painterResource(Res.drawable.app_icon),
                appVersionName = "1.0.0",
                buildDetails = persistentListOf(100, "flavor"),
            )
        }

        item {
            ToolkitDeveloperBanner(
                isDarkTheme = isDarkTheme,
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
            Text(
                text = stringResource(Res.string.increment),
                color = ConcreteTheme.colors.onPrimary,
            )
        }
        CounterText(count)
        Button(
            onClick = onDecrement,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(Res.string.decrement),
                color = ConcreteTheme.colors.onPrimary,
            )
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
            fontSize = 64.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
private fun DarkThemeSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) }
    ) {
        Text(text = "Dark Theme")
        Spacer(Modifier.weight(1f))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }
}
