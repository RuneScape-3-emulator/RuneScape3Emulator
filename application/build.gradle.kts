plugins {
    application
}

application {
    mainClass.set("org.rs3emulator.Application")
}

dependencies {
    api(project(":server"))
}