plugins {
    id("redmadrobot.android-library")
    id("redmadrobot.publish")
}

description = "Use ViewBinding for bindings in ItemsAdapter"

dependencies {
    api(project(":itemsadapter"))

    val viewBindingVersion = "4.1.0"
    api("androidx.databinding:viewbinding:$viewBindingVersion")
    implementation("com.redmadrobot.extensions:viewbinding-ktx:$viewBindingVersion-0")
}
