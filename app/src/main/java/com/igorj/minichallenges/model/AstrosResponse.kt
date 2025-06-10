package com.igorj.minichallenges.model

import kotlinx.serialization.Serializable

@Serializable
data class AstrosResponse(
    val people: List<AstrosPerson>,
    val number: Int,
    val message: String
)

@Serializable
data class AstrosPerson(
    val craft: String,
    val name: String
)