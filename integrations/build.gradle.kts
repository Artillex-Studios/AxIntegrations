plugins {
    id("java")
}

group = "com.artillexstudios.axintegrations.integrations"
version = rootProject.version

subprojects {
    dependencies {
        implementation(project(":common"))
    }
}