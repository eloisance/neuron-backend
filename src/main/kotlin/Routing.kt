package com.neuron

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.neuron.repository.ChallengeRepository
import com.neuron.model.ChallengeResult

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/challenges-result") {
            val challengesResults = ChallengeRepository.allChallengesResults()
            call.respondText(challengesResults.toString())
        }
        post("/challenge-result") {
            val challengeResult = ChallengeResult("4", "1", "35+20", 40L)
            ChallengeRepository.addChallengeResult(challengeResult)
            call.respondText("Challenge result added")
        }
    }
}
