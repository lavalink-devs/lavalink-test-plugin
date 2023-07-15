import java.io.ByteArrayOutputStream
import java.net.URI

plugins {
    java
    `maven-publish`
    alias(libs.plugins.lavalink)
}

group = "dev.arbjerg.test.plugin"
val (versionStr, isSnapshot) = getGitVersion()
version = versionStr
println("Version: $versionStr")


lavalinkPlugin {
    name = "test-plugin"
    apiVersion = libs.versions.lavalink.api
    serverVersion = gitHash(libs.versions.lavalink.server)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
}

val isMavenDefined = findProperty("MAVEN_USERNAME") != null && findProperty("MAVEN_PASSWORD") != null

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
    if (isMavenDefined) {
        println("Publishing to Maven Repo")
        repositories {
            val snapshots = "https://maven.arbjerg.dev/snapshots"
            val releases = "https://maven.arbjerg.dev/releases"

            maven {
                name = "Reposilite"
                url = URI(if (isSnapshot)  snapshots else releases)
                credentials {
                    username = findProperty("MAVEN_USERNAME") as String
                    password = findProperty("MAVEN_PASSWORD") as String
                }
            }
        }
    } else {
        println("Maven credentials not found, not publishing to Maven Repo")
    }
}

fun getGitVersion(): Pair<String, Boolean> {
    var versionStr = ByteArrayOutputStream()
    var result = exec {
        standardOutput = versionStr
        isIgnoreExitValue = true
        commandLine = listOf("git", "describe", "--exact-match", "--tags")
    }
    if (result.exitValue == 0) {
        return Pair(versionStr.toString().trim(), false)
    }

    versionStr = ByteArrayOutputStream()
    result = exec {
        standardOutput = versionStr
        isIgnoreExitValue = true
        commandLine = listOf("git", "rev-parse", "--short", "HEAD")
    }
    if (result.exitValue != 0) {
        throw GradleException("Failed to get git version")
    }

    return Pair(versionStr.toString().trim(), true)
}

fun getCommitHash(): String {
    val p = Runtime.getRuntime().exec("git rev-parse HEAD")
    p.waitFor()
    p.inputStream.bufferedReader().use {
        return it.readText().trim()
    }
}
