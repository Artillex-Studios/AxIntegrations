plugins {
    id("java")
}

repositories {
    maven("https://repo.essentialsx.net/releases")
}

dependencies {
    compileOnly("net.essentialsx:EssentialsX:2.21.2") {
        exclude("io.papermc", "paperlib")
        exclude("io.papermc", "paper-api")
        exclude("io.papermc.paper", "paper-api")
    }
}