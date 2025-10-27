import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val artifactId = "compose"

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.compose)
}

kotlin {
    explicitApi()

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
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

                implementation(compose.components.resources)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation(compose.ui)

                implementation(libs.koin.compose)
                implementation(libs.koin.core)
            }
        }
        val nonWasmMain by creating {
            dependsOn(commonMain.get())
        }
        val jvmAndroidMain by creating {
            dependsOn(nonWasmMain)
            dependencies {
                implementation(compose.components.uiToolingPreview)
                implementation(compose.uiTooling)
            }
        }
        androidMain {
            dependsOn(jvmAndroidMain)
            dependencies {
                implementation(libs.jetbrains.kotlinx.coroutines.android)
            }
        }
        jvmMain {
            dependsOn(jvmAndroidMain)
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        iosMain {
            dependsOn(nonWasmMain)
        }
    }
}

android {
    namespace = "glass.yasan.toolkit.$artifactId"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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
