plugins {
    id("java")
}

dependencies {
    compileOnly("com.github.SaberLLC:Saber-Factions:4.1.4-STABLE") {
        exclude("de.tr7zw", "item-nbt-api")
        exclude("com.mojang", "brigadier")
        exclude("com.mojang", "authlib")
    }
}