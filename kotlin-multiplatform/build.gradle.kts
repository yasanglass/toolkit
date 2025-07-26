plugins {
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.multiplatform) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.vanniktech.maven.publish) apply false
}

allprojects {
    group = "glass.yasan.toolkit"
    version = "0.1.1"
}

subprojects {
    apply(plugin = "com.vanniktech.maven.publish")
    
    configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
        publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL)
        
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