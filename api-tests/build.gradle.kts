plugins {
    id("java")
}

group = "com.glebgol"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}