plugins {
    id("java")
}

repositories {
    maven("https://repo.oraxen.com/releases/")
}

dependencies {
    compileOnly("io.th0rgal:oraxen:1.210.0")
}