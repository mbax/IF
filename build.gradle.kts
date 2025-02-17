plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://libraries.minecraft.net/")
    }
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    testImplementation(libs.org.junit.jupiter.junit.jupiter.engine)
    compileOnly(libs.org.jetbrains.annotations)
    compileOnly(libs.io.papermc.paper.paper.api)
    compileOnly(libs.com.mojang.authlib)
}

group = "dev.kitteh.forkedproject"
version = "0.10.19"
java.sourceCompatibility = JavaVersion.VERSION_21

publishing {
    publications.create<MavenPublication>("IF") {
        from(components["java"])
        groupId = project.group.toString()
        artifactId = "IF-ChestOnly"
        version = project.version.toString()
        pom {
            name = "IF-ChestOnly"
            description = "A Chest GUI provider"
            licenses {
                license {
                    name = "Unlicense"
                    url = "http://unlicense.org"
                }
            }
            developers {
                developer {
                    id = "stefvanschie"
                    name = "Stef van Schie"
                    email = "stefvanschiedev@gmail.com"
                    url = "https://github.com/stefvanschie"
                    roles = setOf("Everything")
                }
                developer {
                    id = "mbaxter"
                    name = "Matt Baxter"
                    email = "matt@kitteh.org"
                    url = "https://www.kitteh.org/"
                    organization = "Kitteh"
                    organizationUrl = "https://kitteh.dev"
                    roles = setOf("Stripping project down to Chests-only")
                }
            }
            issueManagement {
                system = "GitHub"
                url = "https://github.com/mbax/IF-ChestOnly/issues"
            }
            scm {
                connection = "scm:git:git://github.com/mbax/IF-ChestOnly.git"
                developerConnection = "scm:git:git://github.com/mbax/IF-ChestOnly.git"
                url = "git@github.com:mbax/IF-ChestOnly.git"
            }
            repositories {
                maven {
                    name = "DependencyDownload"
                    val rel = "https://dependency.download/releases"
                    val snap = "https://dependency.download/snapshots"
                    url = uri(if (version.toString().endsWith("SNAPSHOT")) snap else rel)
                    credentials(PasswordCredentials::class)
                }
            }
        }
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
