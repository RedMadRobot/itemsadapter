import com.redmadrobot.build.extension.*

plugins {
    val infrastructureVersion = "0.4.1"
    id("redmadrobot.kotlin-library") version infrastructureVersion apply false
    id("redmadrobot.publish") version infrastructureVersion apply false
    id("redmadrobot.detekt") version infrastructureVersion
    `maven-publish`
}

redmadrobot {
    android.minSdk = 14
}

subprojects {
    group = "com.redmadrobot.itemsadapter"
    version = "1.0-SNAPSHOT"

    apply(plugin = "maven-publish")

    publishing {
        repositories {
            if (isRunningOnCi) githubPackages("RedMadRobot/itemsadapter")
            if (!isSnapshotVersion && credentialsExist("bintray")) rmrBintray(project.name)
        }
    }
}
