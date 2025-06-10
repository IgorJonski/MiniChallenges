package com.igorj.minichallenges.ui.model

data class FlashCardData(
    val craftName: String,
    val crew: List<String>
) {
    val crewCount: Int
        get() = crew.size
}