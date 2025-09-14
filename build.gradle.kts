plugins {
    kotlin("jvm") version "2.2.0"
    `java-library`
    `maven-publish`
}

group = "org.slimecraft"
version = "1.0-SNAPSHOT"

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.slimecraft"
            artifactId = "funmands-core"
            version = "1.0-SNAPSHOT"

            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
}

dependencies {
    compileOnly("com.mojang:brigadier:1.0.18")
    compileOnly("net.kyori:adventure-api:4.24.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}