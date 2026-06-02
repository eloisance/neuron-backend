package com.neuron.model

import kotlinx.serialization.Serializable

@Serializable
data class ChallengeResultDto(
    val challengeText: String,
    val time: Long,
)