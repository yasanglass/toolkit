plugins {
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.multiplatform) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.vanniktech.maven.publish) apply false
    alias(libs.plugins.arturbosch.detekt) apply true
}

detekt {
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("$projectDir/detekt/detekt.yml")
    autoCorrect = true
}

allprojects {
    group = "glass.yasan.toolkit"
    version = "0.1.6"
}

subprojects {
    apply(plugin = "com.vanniktech.maven.publish")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        buildUponDefaultConfig = true
        config.setFrom("$rootDir/detekt/detekt.yml")
        source.from(
            "src/androidMain/kotlin",
            "src/androidTest/kotlin",
            "src/commonMain/kotlin",
            "src/commonTest/kotlin",
            "src/iosMain/kotlin",
            "src/iosTest/kotlin",
            "src/jvmAndroidMain/kotlin",
            "src/jvmMain/kotlin",
            "src/jvmTest/kotlin"
        )
    }

    configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
        publishToMavenCentral()
        
        signAllPublications()
        
        pom {
            name.set("Toolkit")
            description.set("Personal development toolkit for Kotlin Multiplatform")
            inceptionYear.set("2025")
            url.set("https://github.com/yasanglass/toolkit/")
            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    distribution.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    id.set("yasanglass")
                    name.set("Yasan Glass")
                    url.set("https://github.com/yasanglass/")
                }
            }
            scm {
                url.set("https://github.com/yasanglass/toolkit/")
                connection.set("scm:git:git://github.com/yasanglass/toolkit.git")
                developerConnection.set("scm:git:ssh://git@github.com/yasanglass/toolkit.git")
            }
        }
    }
}