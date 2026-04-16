plugins {
    id("java")
    id("com.gradleup.shadow") version("9.3.1")
    `maven-publish`
}

group = "com.artillexstudios.axintegrations"
version = "7"

allprojects {
    apply {
        plugin("java")
    }

    repositories {
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://repo.artillex-studios.com/releases/")
        maven("https://jitpack.io/")
    }

    dependencies {
        compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")
        compileOnly("com.artillexstudios.axapi:axapi:1.4.840:all")
    }
}

tasks {
    jar {
        enabled = false
    }

    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveClassifier.set("")
        mergeServiceFiles()

        subprojects.forEach { sub ->
            from(sub.sourceSets.main.get().output)
        }
    }
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.javadoc)
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.artillexstudios.axintegrations"
            artifactId = "AxIntegrations"
            version = "$version"

            artifact(tasks.shadowJar.get())
        }
    }

    repositories {
        maven {
            name = "Artillex-Studios"
            url = uri("https://repo.artillex-studios.com/releases/")
            credentials {
                username = (findProperty("maven_username") as String?) ?: System.getenv("MAVEN_USERNAME")
                password = (findProperty("maven_password") as String?) ?: System.getenv("MAVEN_PASSWORD")
            }
        }
    }
}