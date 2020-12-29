import com.redmadrobot.build.extension.*

plugins {
    id("redmadrobot.root-project") version "0.6"
    `maven-publish`
}

apply(plugin = "redmadrobot.detekt")

redmadrobot {
    android.minSdk = 14
}

subprojects {
    group = "com.redmadrobot.itemsadapter"
    version = "1.1-SNAPSHOT"

    apply(plugin = "maven-publish")

    publishing {
        repositories {
            if (isRunningOnCi) githubPackages("RedMadRobot/itemsadapter")
            if (!isSnapshotVersion && credentialsExist("bintray")) rmrBintray(project.name)
        }
    }
}
