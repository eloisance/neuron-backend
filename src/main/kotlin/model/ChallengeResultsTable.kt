package com.neuron.model

import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

object ChallengeResultsTable : IntIdTable(name = "challenge_results") {
    val challengeText = varchar(name = "challenge_text", length = 128)
    val time = long(name = "time")
}