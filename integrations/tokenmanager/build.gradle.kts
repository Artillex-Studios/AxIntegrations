plugins {
    id("java")
}

dependencies {
    compileOnly("com.github.Realizedd:TokenManager:3.2.3") {
        exclude("net.milkbowl.vault", "VaultAPI")
        exclude("me.clip", "placeholderapi")
        exclude("be.maximvdw", "MVdWPlaceholderAPI")
        exclude("org.bstats", "bstats-bukkit")
    }
}