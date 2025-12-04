package com.neuron

import com.neuron.model.ChallengeResultsTable
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.r2dbc.R2dbcDatabase
import org.jetbrains.exposed.v1.r2dbc.SchemaUtils
import org.jetbrains.exposed.v1.r2dbc.transactions.suspendTransaction

fun main(args: Array<String>) {
    EngineMain.main(args)
}

suspend fun Application.mainModule() {
    val db = getDatabase()
    configureSerialization()
    configureRouting(db = db)
    setupDb(db = db)
}

private suspend fun setupDb(db: R2dbcDatabase) {
    suspendTransaction(db) {
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(tables = arrayOf(ChallengeResultsTable))
    }
}