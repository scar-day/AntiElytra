import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")

    id("de.eldoria.plugin-yml.paper") version "0.7.1"
    id("com.gradleup.shadow") version("8.3.1")
}

group = "dev.scarday"
version = "1.0"

repositories {
    mavenCentral()

    maven { url = uri("https://oss.sonatype.org/content/groups/public/") }
    maven { url = uri("https://storehouse.okaeri.eu/repository/maven-public/") }
    maven { url = uri("https://repo.panda-lang.org/releases") }
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")

    compileOnly("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.8")
    paperLibrary("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.8")

    compileOnly("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.8")
    paperLibrary("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.8")

//    implementation("dev.rollczi:litecommands-bukkit:3.10.0")

    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
}

tasks.shadowJar {
    archiveBaseName.set(rootProject.name)
    archiveClassifier.set("")
}

//tasks.compileJava {
//    options.compilerArgs.add("-parameters")
//}

paper {
    main = "$group.AntiElytra.AntiElytra"
    loader = "$group.AntiElytra.loader.PluginLibrariesLoader"

    author = "ScarDay"

    apiVersion = "1.21"

    generateLibrariesJson = true
}