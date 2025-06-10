package com.igorj.minichallenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorj.minichallenges.api.AstrosApi
import com.igorj.minichallenges.model.AstrosResponse
import com.igorj.minichallenges.ui.model.FlashCardData
import com.igorj.minichallenges.ui.model.FlashCardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlashCardViewModel : ViewModel() {

    private val astrosApi = AstrosApi()

    private val _uiState = MutableStateFlow<FlashCardState>(FlashCardState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchAstros()
    }

    private fun fetchAstros() {
        viewModelScope.launch {
            _uiState.value = FlashCardState.Loading
            try {
                val response = astrosApi.getAstros()
                val flashCardData = mapResponseToFlashCardData(response)
                _uiState.value = FlashCardState.Success(flashCardData)
            } catch (e: Exception) {
                _uiState.value = FlashCardState.Error(e.message ?: "Error occurred")
            }
        }
    }

    private fun mapResponseToFlashCardData(response: AstrosResponse): List<FlashCardData> {
        return response.people
            .groupBy { it.craft }
            .map { (craftName, people) ->
                FlashCardData(
                    craftName = craftName,
                    crew = people.map { it.name }
                )
            }
    }
}