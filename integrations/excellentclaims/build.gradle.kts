plugins {
    id("java")
}

repositories {
    maven("https://repo.nightexpressdev.com/releases")
}

dependencies {
    compileOnly("su.nightexpress.excellentclaims:ExcellentClaims:1.5.2")
}