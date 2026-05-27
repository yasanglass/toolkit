import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val artifactId = "about"

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.compose)
}

kotlin {
    explicitApi()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-sensitive-resolution")
    }

    android {
        namespace = "glass.yasan.toolkit.compose.about"
        compileSdk = libs.versions.android.sdk.compile.get().toInt()
        minSdk = libs.versions.android.sdk.min.get().toInt()

        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }

        androidResources { enable = true }
    }
    jvm()
    iosArm64()
    iosSimulatorArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    js {
        browser()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain {
            dependencies {
                api(project(":compose"))
                api(libs.jetbrains.kotlinx.collections.immutable)

                implementation(libs.glass.yasan.kepko.component)
                implementation(libs.glass.yasan.kepko.foundation)

                implementation(libs.jetbrains.compose.components.resources)
                implementation(libs.jetbrains.compose.components.ui.tooling.preview)
                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.material.icons.extended)
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.ui)

                implementation(libs.coil.compose)
                implementation(libs.coil.svg)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.koin.compose)
                implementation(libs.koin.core)
                implementation(libs.ktor.http)
            }
        }
    }
}

dependencies {
    "androidRuntimeClasspath"(libs.jetbrains.compose.ui.tooling)
}

compose.resources {
    publicResClass = false
    packageOfResClass = "glass.yasan.toolkit.compose.about"
    generateResClass = auto
}

configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
    coordinates(artifactId = artifactId)
}
