plugins {
    id("java")
}

repositories {
    maven("https://repo.nightexpressdev.com/releases")
}

dependencies {
    compileOnly("su.nightexpress.excellentshop:api:5.1.2")
    compileOnly("su.nightexpress.excellentshop:ExcellentShop-spigot:5.1.2")
    compileOnly("su.nightexpress.excellentshop:Core:5.1.2")
    compileOnly("su.nightexpress.nightcore:main:2.16.2")
}