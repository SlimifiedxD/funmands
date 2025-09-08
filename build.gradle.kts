plugins {
    kotlin("jvm") version "2.2.0"
}

group = "org.slimecraft"
version = "1.0-SNAPSHOT"

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