plugins {
    id("java")
}

repositories {
    maven("https://nexus.iridiumdevelopment.net/repository/maven-releases/")
}

dependencies {
    compileOnly("com.iridium:IridiumSkyblock:4.1.3-b5")
}