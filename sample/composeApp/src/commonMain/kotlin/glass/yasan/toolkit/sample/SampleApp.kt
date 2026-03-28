package glass.yasan.toolkit.sample

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import glass.yasan.kepko.foundation.annotation.ExperimentalKepkoApi
import glass.yasan.kepko.foundation.system.SystemBarColorsEffect
import glass.yasan.kepko.foundation.theme.KepkoTheme
import glass.yasan.kepko.persistence.PersistentKepkoTheme
import glass.yasan.toolkit.compose.viewmodel.ViewActionEffect
import glass.yasan.toolkit.compose.viewmodel.rememberSendViewEvent
import glass.yasan.toolkit.core.url.UrlLauncher
import kotlinx.coroutines.launch
import glass.yasan.toolkit.sample.SampleViewModel.Action
import glass.yasan.toolkit.sample.SampleViewModel.Event
import glass.yasan.toolkit.sample.SampleViewModel.State
import glass.yasan.toolkit.sample.navigation.SampleNavHost
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SampleApp() {
    val viewModel: SampleViewModel = koinViewModel()
    val viewState: State by viewModel.viewState.collectAsState()
    val sendViewEvent: (Event) -> Unit = rememberSendViewEvent(viewModel)

    val urlLauncher: UrlLauncher = koinInject()
    val scope: CoroutineScope = rememberCoroutineScope()

    SampleApp(
        viewState = viewState,
        sendViewEvent = sendViewEvent,
    )

    ViewActionEffect(viewModel.viewAction) { action ->
        when (action) {
            is Action.LaunchUrl -> scope.launch { urlLauncher.launch(action.url) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalKepkoApi::class)
@Composable
private fun SampleApp(
    viewState: State,
    sendViewEvent: (Event) -> Unit,
) {
    val navController = rememberNavController()

    PersistentKepkoTheme {
        SystemBarColorsEffect(
            statusBarBackgroundColor = KepkoTheme.colors.foreground,
            navigationBarBackgroundColor = KepkoTheme.colors.midground,
        )

        SampleNavHost(
            navController = navController,
            viewState = viewState,
            sendViewEvent = sendViewEvent,
        )
    }
}
