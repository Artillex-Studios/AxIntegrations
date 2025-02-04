plugins {
    id("java")
    id("maven-publish")
}

group = "com.artillexstudios.axintegrations"
version = "1.2.1"

repositories {
    mavenCentral()

    maven("https://jitpack.io/")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.william278.net/releases")
    maven("https://repo.essentialsx.net/releases/")
//    maven("https://repo.nightexpressdev.com/releases")
    maven("https://repo.artillex-studios.com/releases/")
    maven("https://repo.bg-software.com/repository/api/")
    maven("https://repo.glaremasters.me/repository/towny/")
    maven("https://repo.rosewooddev.io/repository/public/")
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.techscode.com/repository/techscode-apis/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    // protection
    implementation(platform("com.intellectualsites.bom:bom-newest:1.35"))
    compileOnly("com.intellectualsites.plotsquared:plotsquared-bukkit:7.0.0-rc.4")
    compileOnly("com.intellectualsites.plotsquared:plotsquared-core:7.0.0-rc.4")
    compileOnly("com.github.SaberLLC:Saber-Factions:4.1.4-STABLE")
    compileOnly("net.william278.huskclaims:huskclaims-bukkit:1.5")
    compileOnly("net.william278.husktowns:husktowns-bukkit:3.0.8")
    compileOnly("com.github.TechFortress:griefprevention:17.0.0")
    compileOnly("com.palmergames.bukkit.towny:towny:0.101.1.2")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.9")
    compileOnly("com.bgsoftware:SuperiorSkyblockAPI:2024.4")
    compileOnly("com.github.angeschossen:LandsAPI:7.11.10")
    compileOnly("world.bentobox:bentobox:2.7.0-SNAPSHOT")

    // shop prices
    compileOnly("com.github.brcdev-minecraft:shopgui-api:3.0.0")
    compileOnly("com.github.Gypopo:EconomyShopGUI-API:1.7.3")
    compileOnly("net.essentialsx:EssentialsX:2.20.1")
    compileOnly("com.github.Maxlego08:zMenu-API:1.0.3.7")
    compileOnly("com.github.Maxlego08:zShop-API:3.0.5") {
        exclude(group = "com.github.Maxlego08", module = "zMenu-API")
    }
    compileOnly("com.artillexstudios:AxGensAPI:13") {
        exclude(group = "com.artillexstudios.axapi", "axapi")
    }

    // currency
//    compileOnly("com.github.Emibergo02:RedisEconomy:4.3.19")
//    compileOnly("com.github.Realizedd:TokenManager:3.2.4")
//    compileOnly("me.TechsCode:UltraEconomyAPI:1.1.2")
//    compileOnly("org.black_ixx:playerpoints:3.3.0")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")

    compileOnly("org.spigotmc:spigot-api:1.21-R0.1-SNAPSHOT")
    compileOnly(fileTree("$projectDir/libraries"))
}

publishing {
    repositories {
        maven {
            name = "Artillex-Studios"
            url = uri("https://repo.artillex-studios.com/releases/")
            credentials(PasswordCredentials::class) {
                username = project.properties["maven_username"].toString()
                password = project.properties["maven_password"].toString()
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            artifactId = "axintegrations"

            from(components["java"])
        }
    }
}