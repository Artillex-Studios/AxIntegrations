plugins {
    id("java")
}

repositories {
    maven("https://repo.glaremasters.me/repository/public/")
}

dependencies {
    compileOnly("me.glaremasters:guilds:3.5.7.0")
}