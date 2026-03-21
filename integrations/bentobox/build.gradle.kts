plugins {
    id("java")
}

repositories {
    maven("https://repo.codemc.io/repository/bentoboxworld/")
}

dependencies {
    compileOnly("world.bentobox:bentobox:3.10.1-SNAPSHOT")
    compileOnly("world.bentobox:bank:1.9.0-SNAPSHOT")

//    {
//        exclude("com.github.Maxlego08", "zMenu-API")
//    }
}