plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.5.2"
    id("idea")
}

group = "com.jcduhdt.sharp"
version = "1.0-SNAPSHOT"

repositories {
//    mavenCentral()
    mavenLocal()
    maven(url =  uri("https://repo.spring.io/libs-spring-framework-build"))
    maven(url =  uri("https://oss.jfrog.org/artifactory/oss-snapshot-local"))
    maven(url =  uri("https://maven.aliyun.com/nexus/content/groups/public/"))
    maven(url =  uri("https://maven.aliyun.com/nexus/content/repositories/jcenter"))
    maven (url = uri("https://repo.spring.io/release"))
    mavenCentral()
}

//dependencies {
//    implementation("org.springframework:spring-core:5.3.20")
//    implementation("org.springframework:spring-beans:5.3.20")
//    implementation("org.springframework:spring-web:5.3.20")
//}

// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    version.set("2021.2")
    type.set("GO") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
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
