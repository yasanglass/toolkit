plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.plugin.compose)
}

kotlin {
    androidLibrary {
        namespace = "glass.yasan.toolkit.kotlin.multiplatform.compose"
        minSdk = libs.versions.android.sdk.min.get().toInt()
        compileSdk = libs.versions.android.sdk.compile.get().toInt()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.lifecycle.viewmodel)
            }
        }
    }
}