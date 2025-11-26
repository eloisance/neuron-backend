package com.neuron.model

data class ChallengeResult(
    val id: String,
    val userId: String,
    val challengeText: String,
    val time: Long,
)