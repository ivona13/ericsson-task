plugins {
    java
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.google.cloud.tools.jib") version "3.4.3"
}

group = "eu.ericsson"
version = "0.0.1-SNAPSHOT"

var lombokVersion = "1.18.30"
var openApiVersion = "2.6.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

jib {
    from {
        image = "openjdk:21-jdk-slim"
        platforms {
            platform {
                os = "linux"
                architecture = "arm64"
            }
        }
    }
    to {
        image = "ericsson/task"
    }
}


tasks.getByName<Jar>("jar") {
    enabled = false
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${openApiVersion}")
    implementation("org.springframework.boot:spring-boot-starter-security")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testCompileOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
