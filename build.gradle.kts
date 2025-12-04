plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.neuron"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

val envFile = file(".env")
if (envFile.exists()) {
    envFile.readLines().forEach { line ->
        if (line.isNotBlank() && !line.startsWith("#")) {
            val (key, value) = line.split("=", limit = 2)
            // Inject into the 'run' task
            tasks.withType<JavaExec> {
                environment(key.trim(), value.trim())
            }
        }
    }
}

dependencies {
    // Core
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Exposed (R2DBC)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.r2dbc)

    // PostgreSQL (R2DBC)
    implementation(libs.postgresql.r2dbc)

    // Testing
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}
