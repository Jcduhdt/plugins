plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.5.2"
    id("idea")
}

group = "com.jcduhdt.sharp"
version = "1.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    version.set("2021.2")
    type.set("GO") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
    localPath.set("/Users/iveszhang/Library/Application Support/JetBrains/Toolbox/apps/Goland/ch-0/221.5787.30/Goland.app/Contents")
}

dependencies {
    implementation("org.freemarker:freemarker:2.3.31")
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("212")
        untilBuild.set("222.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
