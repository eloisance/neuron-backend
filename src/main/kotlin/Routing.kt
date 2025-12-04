package com.neuron

import com.neuron.model.ChallengeResultDto
import com.neuron.model.ChallengeResultsTable
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.flow.toList
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.r2dbc.R2dbcDatabase
import org.jetbrains.exposed.v1.r2dbc.insert
import org.jetbrains.exposed.v1.r2dbc.selectAll
import org.jetbrains.exposed.v1.r2dbc.transactions.suspendTransaction

fun Application.configureRouting(db: R2dbcDatabase) {
    routing {
        get(path = "/") {
            call.respondText("Hello World!")
        }
        get(path = "/challenges-result") {
            val result: List<ChallengeResultDto> = suspendTransaction(db) {
                addLogger(StdOutSqlLogger)
                ChallengeResultsTable.selectAll().toList().map {
                    ChallengeResultDto(
                        id = it[ChallengeResultsTable.id].value,
                        challengeText = it[ChallengeResultsTable.challengeText],
                        time = it[ChallengeResultsTable.time],
                    )
                }
            }
            call.respond(result)
        }
        post(path = "/challenge-result") {
            suspendTransaction(db) {
                addLogger(StdOutSqlLogger)
                ChallengeResultsTable.insert {
                    it[challengeText] = "1+2"
                    it[time] = 344L
                }
            }
            call.respondText("Challenge result added")
        }
    }
}
