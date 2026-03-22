plugins {
    id("java")
}

dependencies {
    compileOnly("com.artillexstudios:AxShulkers:1.22.3") {
        exclude("net.byteflux", "libby-bukkit")
        exclude("de.tr7zw", "item-nbt-api")
    }
}