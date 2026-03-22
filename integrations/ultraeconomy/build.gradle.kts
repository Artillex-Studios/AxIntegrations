plugins {
    id("java")
}

repositories {
    maven("https://repo.techscode.com/repository/techscode-apis/")
}

dependencies {
    compileOnly("me.TechsCode:UltraEconomyAPI:1.1.2")
}