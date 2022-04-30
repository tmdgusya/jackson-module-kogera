plugins {
    kotlin("jvm") version "1.5.32"
    java
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "com.fasterxml.jackson"
version = "2.13.2-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("com.fasterxml.jackson:jackson-bom:2.13.2")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations
    implementation("com.fasterxml.jackson.core:jackson-annotations")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

kotlin {
    // TODO: enable explicitApi
    // explicitApi()
}

tasks {
    test {
        useJUnitPlatform()
    }
}