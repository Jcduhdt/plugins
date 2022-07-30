// gradle 各种参数使用 https://docs.gradle.org/current/userguide/declaring_dependencies.html
plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.5.2"
    id("idea")
}

group = "com.jcduhdt.sharp"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    maven(url = "https://repo.spring.io/libs-spring-framework-build")
    maven(url = "https://oss.jfrog.org/artifactory/oss-snapshot-local")
    maven(url = "https://maven.aliyun.com/nexus/content/groups/public/")
    maven(url = "https://maven.aliyun.com/nexus/content/repositories/jcenter")
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    version.set("2021.2")

    type.set("GO") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))

    pluginName.set("tauntEveryDay")
    localPath.set("/Users/iveszhang/Library/Application Support/JetBrains/Toolbox/apps/Goland/ch-0/221.5787.30/Goland.app/Contents")
}

// 在maven中央仓库查找依赖 https://mvnrepository.com/artifact/org.springframework/spring-core/5.3.20
dependencies {
    implementation("org.springframework:spring-core:5.3.20")
    implementation("org.springframework:spring-beans:5.3.20")
    implementation("org.springframework:spring-web:5.3.20")
    implementation("com.alibaba:fastjson:1.2.83")
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
