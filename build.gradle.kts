import com.redmadrobot.build.extension.credentialsExist
import com.redmadrobot.build.extension.githubPackages
import com.redmadrobot.build.extension.isSnapshotVersion
import com.redmadrobot.build.extension.rmrBintray

plugins {
    val infrastructureVersion = "0.4.1"
    id("redmadrobot.kotlin-library") version infrastructureVersion apply false
    id("redmadrobot.publish") version infrastructureVersion apply false
    id("redmadrobot.detekt") version infrastructureVersion
    `maven-publish`
}

subprojects {
    group = "com.redmadrobot.itemsadapter"
    version = "1.0-SNAPSHOT"

    apply(plugin = "maven-publish")

    publishing {
        repositories {
            githubPackages("RedMadRobot/itemsadapter")
            if (!isSnapshotVersion && credentialsExist("bintray")) rmrBintray(project.name)
        }
    }
}
