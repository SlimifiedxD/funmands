plugins {
    id("java")
    id("com.gradleup.shadow") version "9.1.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("xyz.jpenilla.resource-factory-paper-convention") version "1.3.1"
}

group = "org.slimecraft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
    implementation(project(":paper"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    runServer {
        minecraftVersion("1.21.8")
    }
}

paperPluginYaml {
    main = "org.slimecraft.funmands.paper.example.FunmandsPaperExamplePlugin"
    apiVersion = "1.21.8"
}

tasks.test {
    useJUnitPlatform()
}