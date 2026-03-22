plugins {
    id("java")
}

repositories {
    maven("https://repo.auxilor.io/repository/maven-public/")
}

dependencies {
    compileOnly("com.willfp:EcoBits:1.8.4")
}