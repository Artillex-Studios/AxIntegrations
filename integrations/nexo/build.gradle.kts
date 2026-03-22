plugins {
    id("java")
}

repositories {
    maven("https://repo.nexomc.com/releases/")
}

dependencies {
    compileOnly("com.nexomc:nexo:1.21.0")
}