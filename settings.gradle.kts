pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "redmadrobot") {
                useModule("com.redmadrobot.build:infrastructure-android:${requested.version}")
            }
        }
    }
}

rootProject.name = "itemsadapter"

include(
    "itemsadapter",
    "itemsadapter-viewbinding"
)
