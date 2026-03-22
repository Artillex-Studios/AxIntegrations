plugins {
    id("java")
}

repositories {
    maven("https://maven.devs.beer/")
}

dependencies {
    compileOnly("dev.lone:api-itemsadder:4.0.10")
}