package glass.yasan.toolkit.sample.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import glass.yasan.kepko.foundation.theme.ThemeStyle
import glass.yasan.toolkit.sample.SampleViewModel

@Composable
internal fun SampleNavHost(
    themeStyle: ThemeStyle,
    onThemeStyleChange: (ThemeStyle) -> Unit,
    navController: NavHostController,
    viewState: SampleViewModel.State,
    sendViewEvent: (SampleViewModel.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home,
        modifier = modifier,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            )
        },
    ) {
        composable<Route.Home> {
            HomeScreen(
                theme = themeStyle,
                onThemeChange = onThemeStyleChange,
                viewState = viewState,
                sendViewEvent = sendViewEvent,
                onNavigateToColors = { navController.navigate(Route.Colors) },
                onNavigateToAbout = { navController.navigate(Route.About) },
            )
        }

        composable<Route.Colors> {
            ColorsScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
            )
        }

        composable<Route.About> {
            AboutScreen(
                onNavigateBack = { navController.navigateUp() },
            )
        }
    }
}
