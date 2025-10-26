plugins {
    id("java")
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.project"
version = "0.0.1-SNAPSHOT"
description = "Have-bin project refactor"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter & Spring WebFlux
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Spring OAuth2 & Security
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
//    implementation("org.springframework.boot:spring-boot-starter-security")

    // MariaDB & Spring JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    // Redis
    // Lettuce는 비동기, Jedis는 동기 → Lettuce는 Jedis보다 성능이 좋아 권장
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Swagger
    // implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:latest.release")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

    // Validation (@NotNull, @Size, @Min, @Max 등)
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // JWT
    implementation("io.jsonwebtoken:jjwt-api:latest.release")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:latest.release")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:latest.release")

    // Mockito - Test 라이브러리
    implementation("org.mockito:mockito-junit-jupiter")

    // AWS S3
    implementation("io.awspring.cloud:spring-cloud-aws-starter-s3:latest.release")
    implementation("software.amazon.awssdk:s3:latest.release")

    // Spring Boot Mail Starter
    implementation("org.springframework.boot:spring-boot-starter-mail")

    // CSV Reader (테스트용)
    implementation("com.opencsv:opencsv:latest.release")

    // Tsid Creator
    implementation("io.hypersistence:hypersistence-utils-hibernate-60:latest.release")
    //implementation("com.github.f4b6a3:tsid-creator-jpa:5.0.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootJar {
    archiveFileName.set("havebin.jar")
}