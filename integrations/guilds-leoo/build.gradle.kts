plugins {
    id("java")
}

repositories {
    maven("https://repo.glaremasters.me/repository/public/")
}

dependencies {
    compileOnly("com.github.PixelStudiosDev:GuildsAPI:1.1.2-SNAPSHOT")
}