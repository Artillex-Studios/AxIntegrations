plugins {
    id("java")
}

repositories {
    maven("https://repo.william278.net/releases/")
}

dependencies {
    compileOnly("net.william278.husktowns:husktowns-bukkit:3.1.4")
}