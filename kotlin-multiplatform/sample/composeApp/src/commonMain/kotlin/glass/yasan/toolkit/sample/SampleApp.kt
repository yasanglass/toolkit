package glass.yasan.toolkit.sample

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import glass.yasan.toolkit.about.presentation.compose.DeveloperBrandingFooter
import glass.yasan.toolkit.compose.viewmodel.ViewActionEffect
import org.jetbrains.compose.resources.stringResource
import glass.yasan.toolkit.compose.viewmodel.rememberSendViewEvent
import glass.yasan.toolkit.composeapp.generated.resources.Res
import glass.yasan.toolkit.composeapp.generated.resources.app_title
import glass.yasan.toolkit.composeapp.generated.resources.decrement
import glass.yasan.toolkit.composeapp.generated.resources.increment
import glass.yasan.toolkit.core.url.UrlLauncher
import glass.yasan.toolkit.sample.SampleViewModel.Action
import glass.yasan.toolkit.sample.SampleViewModel.Event
import glass.yasan.toolkit.sample.SampleViewModel.State
import glass.yasan.toolkit.sample.theme.AppTheme
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SampleApp() {
    val viewModel: SampleViewModel = koinViewModel()
    val viewState: State by viewModel.viewState.collectAsState()
    val sendViewEvent: (Event) -> Unit = rememberSendViewEvent(viewModel)

    val urlLauncher: UrlLauncher = koinInject()

    SampleApp(
        viewState = viewState,
        sendViewEvent = sendViewEvent,
    )

    ViewActionEffect(viewModel.viewAction) { action ->
        when (action) {
            is Action.LaunchUrl -> urlLauncher.launch(action.url)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SampleApp(
    viewState: State,
    sendViewEvent: (Event) -> Unit,
) {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(Res.string.app_title),
                            fontWeight = FontWeight.Black,
                        )
                    },
                )
            },
        ) { contentPadding ->
            LazyColumn(
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp),
            ) {
                viewEventItem(viewState, sendViewEvent)
                item { HorizontalDivider() }
                developerItem(viewState, sendViewEvent)
                item { HorizontalDivider() }
                item { DeveloperBrandingFooter() }
            }
        }
    }
}

private fun LazyListScope.viewEventItem(
    viewState: State,
    sendViewEvent: (Event) -> Unit,
) {
    item {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { sendViewEvent(Event.Increment) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(Res.string.increment))
            }
            CounterText(viewState.count)
            Button(
                onClick = { sendViewEvent(Event.Decrement) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(Res.string.decrement))
            }
        }
    }
}

private fun LazyListScope.developerItem(
    viewState: State,
    sendViewEvent: (Event) -> Unit,
) {
    item {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            viewState.developer.links.forEach { link ->
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    ),
                    onClick = { sendViewEvent(Event.DeveloperLinkClick(link)) },
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = link.name)
                        Spacer(Modifier.weight(1f))
                        link.Icon()
                    }
                }
            }
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
                    .togetherWith(slideOutVertically(animationSpec = tween(animationDuration)) { height -> -height } +
                            fadeOut(animationSpec = tween(animationDuration / 2)))
            } else {
                (slideInVertically(animationSpec = tween(animationDuration)) { height -> -height } +
                        fadeIn(animationSpec = tween(animationDuration / 2)))
                    .togetherWith(slideOutVertically(animationSpec = tween(animationDuration)) { height -> height } +
                            fadeOut(animationSpec = tween(animationDuration / 2)))
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
