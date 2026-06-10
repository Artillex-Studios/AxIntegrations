plugins {
    id("java")
}

repositories {
    maven("https://repo.glaremasters.me/repository/towny/")
}

dependencies {
    compileOnly("com.palmergames.bukkit.towny:towny:0.102.0.12")
    compileOnly("net.kyori:adventure-api:5.1.1")
}