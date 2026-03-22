plugins {
    id("java")
}

dependencies {
    compileOnly("com.github.cryptomorin:kingdoms:1.17.18.1-BETA") {
        exclude("org.kingdoms", "shared")
        exclude("org.kingdoms.nbt", "nbt")
        exclude("org.snakeyaml", "snakeyaml")
    }
}