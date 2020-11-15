plugins {
    id("redmadrobot.android-library")
    id("redmadrobot.publish")
}

description = "The simple adapter to render static data in RecyclerView"

dependencies {
    api(androidx.annotation)
    api(androidx.recyclerview)
}
