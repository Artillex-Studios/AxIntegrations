plugins {
    id("java")
}

repositories {
    maven("https://repo.nightexpressdev.com/releases")
}

dependencies {
    compileOnly("su.nightexpress.excellentshop:api:5.0.1")
    compileOnly("su.nightexpress.excellentshop:ExcellentShop-spigot:5.0.1")
    compileOnly("su.nightexpress.excellentshop:Core:5.0.1")
}