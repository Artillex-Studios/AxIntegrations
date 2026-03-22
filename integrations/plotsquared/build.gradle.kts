plugins {
    id("java")
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.enginehub.org/repo/")
}

dependencies {
    compileOnly(platform("com.intellectualsites.bom:bom-newest:1.56"))
    compileOnly("com.intellectualsites.plotsquared:plotsquared-core")
    compileOnly("com.intellectualsites.plotsquared:plotsquared-bukkit") { isTransitive = false }
    compileOnly("com.sk89q.worldedit:worldedit-core:7.4.1")
}