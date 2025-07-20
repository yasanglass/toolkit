import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.vanniktech.maven.publish)
}

group = "glass.yasan.toolkit"
version = "0.0.9"

kotlin {
    explicitApi()
    
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

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.components.resources)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.lifecycle.viewmodel)
            }
        }
        val jvmAndroidMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation(compose.components.uiToolingPreview)
                implementation(compose.uiTooling)
            }
        }
        androidMain {
            dependsOn(jvmAndroidMain)
        }
        jvmMain {
            dependsOn(jvmAndroidMain)
            dependencies {
                implementation(compose.desktop.common)
                implementation(libs.jetbrains.kotlinx.coroutines.swing)
            }
        }
    }
}

android {
    namespace = "glass.yasan.toolkit.compose"
    compileSdk = libs.versions.android.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.sdk.min.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

mavenPublishing {
    publishToMavenCentral(host = SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(
        groupId = group.toString(),
        artifactId = "compose",
        version = version.toString(),
    )

    pom {
        name = "Toolkit"
        description = "Personal development toolkit for Kotlin Multiplatform"
        inceptionYear = "2025"
        url = "https://github.com/yasanglass/toolkit/"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "yasanglass"
                name = "Yasan Glass"
                url = "https://github.com/yasanglass/"
            }
        }
        scm {
            url = "https://github.com/yasanglass/toolkit/"
            connection = "scm:git:git://github.com/yasanglass/toolkit.git"
            developerConnection = "scm:git:ssh://git@github.com/yasanglass/toolkit.git"
        }
    }
}
