plugins {
    id("redmadrobot.android-library")
    id("redmadrobot.publish")
}

description = "Use ViewBinding for bindings in ItemsAdapter"

dependencies {
    api(project(":itemsadapter:itemsadapter"))
    api(androidx.viewbinding)
    implementation(redmadrobot.viewbinding_ktx)
}
