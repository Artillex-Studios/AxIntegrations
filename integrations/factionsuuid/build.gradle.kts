plugins {
    id("java")
}

repositories {
    maven("https://dependency.download/releases")
}

dependencies {
    compileOnly("dev.kitteh:factions:4.5.1")
    compileOnly("net.kyori:adventure-api:5.1.1")
}