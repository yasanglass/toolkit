package glass.yasan.toolkit.sample

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import glass.yasan.concrete.foundation.color.isDynamicAccentSupported
import glass.yasan.concrete.foundation.theme.ConcreteTheme
import glass.yasan.toolkit.compose.viewmodel.ViewActionEffect
import glass.yasan.toolkit.compose.viewmodel.rememberSendViewEvent
import glass.yasan.toolkit.core.url.UrlLauncher
import glass.yasan.toolkit.sample.SampleViewModel.Action
import glass.yasan.toolkit.sample.SampleViewModel.Event
import glass.yasan.toolkit.sample.SampleViewModel.State
import glass.yasan.toolkit.sample.navigation.SampleNavHost
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
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val isDynamicAccentSupported = isDynamicAccentSupported()
    val navController = rememberNavController()

    val isDynamicAccent = rememberSaveable { mutableStateOf(isDynamicAccentSupported) }
    val isDarkTheme = rememberSaveable { mutableStateOf(isSystemInDarkTheme) }

    AppTheme(
        isDark = isDarkTheme.value,
        isDynamicAccentAllowed = isDynamicAccent.value,
    ) {
        Scaffold(
            containerColor = ConcreteTheme.colors.midground,
        ) { contentPadding ->
            SampleNavHost(
                isDarkTheme = isDarkTheme.value,
                onDarkThemeChange = { isDarkTheme.value = it },
                isDynamicAccent = isDynamicAccent.value,
                onDynamicAccentChange = { isDynamicAccent.value = it },
                navController = navController,
                viewState = viewState,
                sendViewEvent = sendViewEvent,
                modifier = Modifier.padding(contentPadding),
            )
        }
    }
}
