plugins {
    id("java")
}

repositories {
    maven("https://repo.alessiodp.com/releases/")
}

dependencies {
    compileOnly("com.alessiodp.parties:parties-api:3.2.16")
}