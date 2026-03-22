plugins {
    id("java")
}

repositories {
    maven("https://repo.groupez.dev/releases")
}

dependencies {
    compileOnly("com.github.Maxlego08:zShop-API:3.0.5") {
        exclude("com.github.Maxlego08", "zMenu-API")
    }
    compileOnly("fr.maxlego08.menu:zmenu-api:1.1.1.2")
}