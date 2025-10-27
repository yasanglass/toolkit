import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val artifactId = "core"

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.library)
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
                implementation(libs.jetbrains.kotlinx.coroutines.core)
            }
        }
        val nonWasmMain by creating {
            dependsOn(commonMain.get())
        }
        jvmMain {
            dependsOn(nonWasmMain)
        }
        androidMain {
            dependsOn(nonWasmMain)
            dependencies {
                implementation(libs.jetbrains.kotlinx.coroutines.android)
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

configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
    coordinates(artifactId = artifactId)
}
