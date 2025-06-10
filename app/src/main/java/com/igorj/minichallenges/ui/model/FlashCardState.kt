package com.igorj.minichallenges.ui.model

sealed class FlashCardState {
    object Loading : FlashCardState()
    data class Success(val data: List<FlashCardData>) : FlashCardState()
    data class Error(val message: String) : FlashCardState()
}