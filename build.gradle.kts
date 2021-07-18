import com.redmadrobot.build.dsl.*

plugins {
    id("redmadrobot.root-project") version "0.11"
    `maven-publish`
}

apply(plugin = "redmadrobot.detekt")

redmadrobot {
    android.minSdk.set(14)

    publishing {
        signArtifacts.set(!isRunningOnCi)

        pom {
            setGitHubProject("RedMadRobot/itemsadapter")

            licenses {
                mit()
            }

            developers {
                developer(id = "osipxd", name = "Osip Fatkullin", email = "o.fatkullin@redmadrobot.com")
            }
        }
    }
}

subprojects {
    group = "com.redmadrobot.itemsadapter"
    version = "1.2-SNAPSHOT"

    apply(plugin = "maven-publish")

    publishing {
        repositories {
            if (isRunningOnCi) githubPackages("RedMadRobot/itemsadapter")
            if (isReleaseVersion && credentialsExist("ossrh")) ossrh()
        }
    }
}
