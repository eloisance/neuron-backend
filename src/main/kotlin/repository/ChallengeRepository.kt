package com.neuron.repository

import com.neuron.model.ChallengeResult

object ChallengeRepository {

    private val challengesResults = mutableListOf<ChallengeResult>(
        ChallengeResult("1", "1", "10+20", 10L),
        ChallengeResult("2", "1", "15+20", 12L),
        ChallengeResult("3", "1", "25+20", 9L),
    )

    fun allChallengesResults(): List<ChallengeResult> = challengesResults

    fun addChallengeResult(challengeResult: ChallengeResult) {
        challengesResults.add(challengeResult)
    }
}

