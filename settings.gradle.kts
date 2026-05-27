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
include(":about")
include(":compose")
include(":core")
include(":koin")
include(":sample")
include(":sample:androidApp")
include(":sample:composeApp")
