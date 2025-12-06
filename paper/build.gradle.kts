plugins {
    kotlin("jvm")
    `java-library`
    `maven-publish`
}

group = "org.slimecraft"
version = "1.0-SNAPSHOT"

java {
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api(project(":"))
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.SlimifiedxD"
            artifactId = "funmands-core"
            version = "1.0-SNAPSHOT"

            from(components["java"])
        }
    }
}