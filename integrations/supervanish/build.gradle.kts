plugins {
    id("java")
}

repositories {
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.LeonMangler:SuperVanish:6.2.18-3") {
        exclude("com.mojang", "brigadier")
    }
}