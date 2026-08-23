pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "toolkit"
include(":compose")
include(":core")
include(":koin")
if (gradle.parent == null) {
    include(":sample")
    include(":sample:androidApp")
    include(":sample:composeApp")
}
