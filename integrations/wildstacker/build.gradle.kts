plugins {
    id("java")
}

repositories {
    maven("https://repo.bg-software.com/repository/api/")
}

dependencies {
    compileOnly("com.bgsoftware:WildStackerAPI:2025.2")
}