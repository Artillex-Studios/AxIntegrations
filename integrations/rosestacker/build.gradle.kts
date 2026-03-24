plugins {
    id("java")
}

repositories {
    maven("https://repo.rosewooddev.io/repository/public/")
}

dependencies {
    compileOnly("dev.rosewood:rosestacker:1.5.39")
}