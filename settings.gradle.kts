pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven(url = "https://dl.bintray.com/redmadrobot-opensource/android")
    }
}

rootProject.name = "itemsadapter"

include(
    "itemsadapter",
    "itemsadapter-viewbinding"
)
