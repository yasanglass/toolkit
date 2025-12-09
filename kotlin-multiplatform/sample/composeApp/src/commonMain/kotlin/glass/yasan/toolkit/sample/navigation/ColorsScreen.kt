package glass.yasan.toolkit.sample.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.concrete.component.TextMono
import glass.yasan.concrete.foundation.theme.ConcreteTheme
import glass.yasan.toolkit.compose.color.toContentColor
import glass.yasan.toolkit.composeapp.generated.resources.Res
import glass.yasan.toolkit.composeapp.generated.resources.arrow_back
import glass.yasan.toolkit.composeapp.generated.resources.go_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ColorsScreen(
    onNavigateBack: () -> Unit,
) {
    val colorsAndNames = listOf(
        ConcreteTheme.colors.primaryHigh to "Primary High",
        ConcreteTheme.colors.primary to "Primary",
        ConcreteTheme.colors.primaryLow to "Primary Low",
        ConcreteTheme.colors.secondaryHigh to "Secondary High",
        ConcreteTheme.colors.secondary to "Secondary",
        ConcreteTheme.colors.secondaryLow to "Secondary Low",
        ConcreteTheme.colors.tertiaryHigh to "Tertiary High",
        ConcreteTheme.colors.tertiary to "Tertiary",
        ConcreteTheme.colors.tertiaryLow to "Tertiary Low",
        ConcreteTheme.colors.onPrimary to "On Primary",
        ConcreteTheme.colors.onSecondary to "On Secondary",
        ConcreteTheme.colors.onTertiary to "On Tertiary",
        ConcreteTheme.colors.foreground to "Foreground",
        ConcreteTheme.colors.midground to "Midground",
        ConcreteTheme.colors.background to "Background",
    )

    Scaffold(
        topBar = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_back),
                    contentDescription = stringResource(Res.string.go_back),
                )
            }
        },
        containerColor = ConcreteTheme.colors.midground,
        modifier = Modifier.fillMaxSize(),
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp),
        ) {
            items(items = colorsAndNames) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(it.first)
                        .padding(16.dp),
                ) {
                    TextMono(
                        text = it.second,
                        color = it.first.toContentColor(),
                    )
                }
            }
        }
    }
}
