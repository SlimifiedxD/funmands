plugins {
    id("java")
    `java-library`
    `maven-publish`
}

group = "org.slimecraft"
version = "1.0-SNAPSHOT"

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "org.slimecraft"
            artifactId = "funmands-velocity"
            version = "1.0-SNAPSHOT"

            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    api(project(":"))
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
