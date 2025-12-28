import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.jetbrains.compose.hotreload)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-sensitive-resolution")
    }

    androidLibrary {
        namespace = "glass.yasan.toolkit.sample"
        compileSdk = libs.versions.sample.android.sdk.compile.get().toInt()
        minSdk = libs.versions.sample.android.sdk.min.get().toInt()

        androidResources.enable = true

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
            }
        }
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":koin"))

                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.lifecycle.viewmodel.compose)
                implementation(libs.androidx.navigation.compose)
                implementation(libs.glass.yasan.kepko.component)
                implementation(libs.glass.yasan.kepko.foundation)
                implementation(libs.jetbrains.kotlinx.serialization.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.koin.core)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.jetbrains.kotlin.test)
                implementation(libs.jetbrains.kotlinx.coroutines.test)
            }
        }
        jvmMain {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.jetbrains.kotlinx.coroutines.swing)
            }
        }
    }
}

compose.resources {
    packageOfResClass = "glass.yasan.toolkit.sample"
    generateResClass = auto
}

compose.desktop {
    application {
        mainClass = "glass.yasan.toolkit.sample.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Toolkit"
            packageVersion = "1.0.0"
            macOS {
                iconFile.set(project.file("src/jvmMain/resources/app_icon.icns"))
                bundleID = "glass.yasan.toolkit.sample"
                dockName = "Toolkit"
            }
            windows {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/app_icon.png"))
            }
            linux {
                iconFile.set(project.file("src/commonMain/composeResources/drawable/app_icon.png"))
            }
        }
    }
}
