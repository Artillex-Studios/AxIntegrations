plugins {
    id("java")
}

repositories {
    maven("https://repo.groupez.dev/releases")
}

dependencies {
    compileOnly("fr.maxlego08.shop:zshop-api:3.3.4") {
        exclude("com.github.Maxlego08", "zMenu-API")
    }
    compileOnly("fr.maxlego08.menu:zmenu-api:1.1.1.2")
}