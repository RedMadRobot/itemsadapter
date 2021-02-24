import com.redmadrobot.build.PublishPlugin.Companion.PUBLICATION_NAME

plugins {
    id("redmadrobot.android-library")
    id("redmadrobot.publish")
}

description = "The simple adapter to render static data in RecyclerView"

publishing.publications.getByName<MavenPublication>(PUBLICATION_NAME) {
    pom.name.set("ItemsAdapter")
}

dependencies {
    api("androidx.annotation:annotation:1.1.0")
    api("androidx.recyclerview:recyclerview:1.1.0")
}
