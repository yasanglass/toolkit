import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val artifactId = "compose"

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
        freeCompilerArgs.add("-Xexpect-actual-classes")
        freeCompilerArgs.add("-Xcontext-sensitive-resolution")
    }

    android {
        namespace = "glass.yasan.toolkit.$artifactId"
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
                api(project(":core"))

                implementation(libs.jetbrains.kotlinx.coroutines.core)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.lifecycle.viewmodel)

                implementation(libs.jetbrains.compose.components.resources)
                implementation(libs.jetbrains.compose.components.ui.tooling.preview)
                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.material.icons.extended)
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.ui)

                implementation(libs.koin.compose)
                implementation(libs.koin.core)
            }
        }
        val nonWebMain by creating {
            dependsOn(commonMain.get())
        }
        jvmMain {
            dependsOn(nonWebMain)
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        androidMain {
            dependsOn(nonWebMain)
            dependencies {
                implementation(libs.jetbrains.kotlinx.coroutines.android)
            }
        }
        iosMain {
            dependsOn(nonWebMain)
        }
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "glass.yasan.toolkit.$artifactId"
    generateResClass = auto
}

configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
    coordinates(artifactId = artifactId)
}
