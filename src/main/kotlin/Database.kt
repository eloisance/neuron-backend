package com.neuron

import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.r2dbc.R2dbcDatabase

fun Application.getDatabase(): R2dbcDatabase {
    val storageConfig = environment.config.config("storage")
    val driverName = storageConfig.property("driverName").getString()
    val r2dbcUrl = storageConfig.property("r2dbcUrl").getString()
    val databaseName = storageConfig.property("databaseName").getString()
    val user = storageConfig.property("username").getString()
    val password = storageConfig.property("password").getString()
    return R2dbcDatabase.connect(
        driver = driverName,
        url = r2dbcUrl + databaseName,
        user = user,
        password = password
    )
}