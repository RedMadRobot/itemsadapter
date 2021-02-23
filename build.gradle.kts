import com.redmadrobot.build.extension.*

plugins {
    id("redmadrobot.root-project") version "0.8"
    `maven-publish`
}

apply(plugin = "redmadrobot.detekt")

redmadrobot {
    android.minSdk = 14
    publishing.signArtifacts = true
}

subprojects {
    group = "com.redmadrobot.itemsadapter"
    version = "1.1"

    apply(plugin = "maven-publish")

    publishing {
        repositories {
            if (isRunningOnCi) githubPackages("RedMadRobot/itemsadapter")
            if (isReleaseVersion && credentialsExist("ossrh")) ossrh()
        }
    }
}
