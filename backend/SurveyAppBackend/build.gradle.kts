plugins {
	java
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com"
version = "0.0.1-SNAPSHOT"
description = "Backend of SurveyApp "

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
  	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-h2console")
	implementation("org.springframework.boot:spring-boot-starter-validation") 
	implementation("io.jsonwebtoken:jjwt-api:0.13.0")
	implementation("io.github.cdimascio:java-dotenv:5.2.2")
	implementation("org.bouncycastle:bcprov-jdk18on:1.83")
	implementation("com.google.code.gson:gson:2.13.2")
	implementation("org.springframework.boot:spring-boot-starter-security")

  	testImplementation("org.springframework.boot:spring-boot-starter-security-test")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.springframework.boot:spring-boot-starter-validation-test")

  	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.13.0")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.13.0")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
