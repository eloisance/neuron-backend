package com.neuron.model

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass

class ChallengeResultEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ChallengeResultEntity>(table = ChallengeResultsTable)

    var challengeText by ChallengeResultsTable.challengeText
    val time by ChallengeResultsTable.time

    override fun toString(): String {
        return "ChallengeResultEntity(id=$id, challengeText=$challengeText, time=$time)"
    }
}