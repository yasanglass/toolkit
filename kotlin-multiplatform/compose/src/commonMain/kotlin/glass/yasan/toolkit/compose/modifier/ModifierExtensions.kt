package glass.yasan.toolkit.compose.modifier

import androidx.compose.ui.Modifier

public inline fun Modifier.addIf(condition: Boolean, crossinline factory: Modifier.() -> Modifier): Modifier =
    if (condition) {
        factory()
    } else {
        this
    }

public inline fun <T> Modifier.addIfNotNull(value: T?, crossinline factory: Modifier.(T) -> Modifier): Modifier =
    if (value != null) {
        factory(value)
    } else {
        this
    }
