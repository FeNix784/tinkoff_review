plugins {
    java
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "ru.tkachev"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.hibernate:hibernate-core:6.3.1.Final")
    implementation("org.hibernate:hibernate-ehcache:5.6.15.Final")
    implementation("javax.transaction:javax.transaction-api:1.3")
    implementation("net.sf.ehcache:ehcache:2.10.9.2")
    implementation("net.sf.ehcache:ehcache-core:2.6.11")

    runtimeOnly("com.h2database:h2")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
