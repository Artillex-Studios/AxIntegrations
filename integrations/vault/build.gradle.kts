plugins {
    id("java")
}

repositories {
    maven("https://repo.nightexpressdev.com/releases")
}

dependencies {
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
}