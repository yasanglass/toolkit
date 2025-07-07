import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.kotlin.plugin.compose)
    alias(libs.plugins.vanniktech.maven.publish)
}

group = "glass.yasan.toolkit.kotlin.multiplatform"
version = "0.0.1"

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
        description = "Personal toolkit for Kotlin Multiplatform"
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
