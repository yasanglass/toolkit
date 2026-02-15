import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.Executable
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-sensitive-resolution")
    }

    androidTarget {
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
        macosX64(),
        macosArm64(),
    ).forEach { macosTarget ->
        macosTarget.binaries.executable {
            entryPoint = "glass.yasan.toolkit.sample.main"
        }
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
        androidMain {
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.koin.android)
            }
        }
    }
}

android {
    namespace = "glass.yasan.toolkit.sample"
    compileSdk = libs.versions.sample.android.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = "glass.yasan.toolkit.sample"
        minSdk = libs.versions.sample.android.sdk.min.get().toInt()
        targetSdk = libs.versions.sample.android.sdk.target.get().toInt()
        versionCode = 1
        versionName = "1.0.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

val macosTargets = kotlin.targets.filterIsInstance<KotlinNativeTarget>().filter { it.name.startsWith("macos") }
for (target in macosTargets) {
    for (executable in target.binaries.filterIsInstance<Executable>()) {
        val taskName = "copyComposeResources" +
            executable.name.replaceFirstChar { it.uppercaseChar() } +
            target.name.replaceFirstChar { it.uppercaseChar() }
        val copyResources = tasks.register<Copy>(taskName) {
            from(tasks.named("${target.name}ProcessResources"))
            into(executable.outputDirectory.resolve("compose-resources"))
        }
        executable.linkTaskProvider.configure { dependsOn(copyResources) }
    }
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
