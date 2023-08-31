import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.10" apply false
}

group = "org.rs3emulator"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {
    repositories {
        mavenCentral()
    }
    apply {
        plugin("kotlin")
        plugin("java")
    }

    val implementation by configurations
    val testImplementation by configurations
    dependencies {
        implementation("io.netty:netty-all:4.1.97.Final")
        implementation("io.insert-koin:koin-core-jvm:3.4.3")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        testImplementation(kotlin("test"))
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}