import com.redmadrobot.build.extension.*

plugins {
    id("redmadrobot.root-project") version "0.8"
    `maven-publish`
}

apply(plugin = "redmadrobot.detekt")

redmadrobot {
    android.minSdk = 14

    publishing {
        signArtifacts = true

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

repositories {
    jcenter() // TODO: Remove when detekt plugins will be moved to Maven Central
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
