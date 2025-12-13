package glass.yasan.toolkit.sample.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import glass.yasan.kepko.component.Icon
import glass.yasan.kepko.component.TextMono
import glass.yasan.kepko.foundation.theme.KepkoTheme
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
        KepkoTheme.colors.content to "Content",
        KepkoTheme.colors.contentSubtle to "Content Subtle",
        KepkoTheme.colors.contentDisabled to "Content Disabled",
        KepkoTheme.colors.foreground to "Foreground",
        KepkoTheme.colors.midground to "Midground",
        KepkoTheme.colors.background to "Background",
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
        containerColor = KepkoTheme.colors.midground,
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
