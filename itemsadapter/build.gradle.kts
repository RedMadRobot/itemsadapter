plugins {
    id("redmadrobot.android-library")
    id("redmadrobot.publish")
}

description = "The simple adapter to render static data in RecyclerView"

dependencies {
    api(kotlin("stdlib", version = "1.5.21"))
    api("androidx.annotation:annotation:1.2.0")
    api("androidx.recyclerview:recyclerview:1.2.1")
}
