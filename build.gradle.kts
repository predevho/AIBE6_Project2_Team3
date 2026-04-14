plugins {
    id("java")
}

group = "com"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // Source: https://mvnrepository.com/artifact/org.assertj/assertj-core
    testImplementation("org.assertj:assertj-core:3.27.3")
}

tasks.test {
    useJUnitPlatform()
}

// 한글 경로 인코딩 버그 픽스: native.encoding=MS949 vs file.encoding=UTF-8 불일치로
// JVM 클래스패스 argfile의 한글 경로가 깨져 ClassNotFoundException 발생하는 문제 해결
layout.buildDirectory.set(
    file("${System.getProperty("user.home").replace("\\", "/")}/.gradle/project-builds/${rootProject.name}")
)