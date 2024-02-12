pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Group00"
include(":app")
include(":feature:thermometer")
include(":feature:primenumbers")
include(":feature:circles")
include(":core:ui")
include(":core:data")
include(":core:logger")
include(":feature:highintensive")
