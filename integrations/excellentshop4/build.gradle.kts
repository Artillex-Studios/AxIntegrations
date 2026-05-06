plugins {
    id("java")
}

repositories {
    maven("https://repo.nightexpressdev.com/releases")
}

dependencies {
    compileOnly("su.nightexpress.excellentshop:api:4.22.0")
    compileOnly("su.nightexpress.excellentshop:ExcellentShop-spigot:4.22.0")
    compileOnly("su.nightexpress.excellentshop:Core:4.22.0")
}