plugins {
    id("java")
}

repositories {
    maven("https://repo.nightexpressdev.com/releases")
}

dependencies {
    compileOnly("su.nightexpress.coinsengine:CoinsEngine:2.7.0")
}