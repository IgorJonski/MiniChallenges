package com.igorj.minichallenges.ui.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DawnDuskScreen(
    modifier: Modifier = Modifier,
    currentRating: Int,
    onRatingChanged: (Int) -> Unit
) {
    val isDarkMode = isSystemInDarkTheme()

    Column(
        modifier = modifier
            .padding(32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = if (isDarkMode) Alignment.CenterEnd else Alignment.CenterStart,
        ) {
            TimeOfDayIcon(modifier = Modifier.size(80.dp))
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            RatingSection(
                currentRating = currentRating,
                onRatingChanged = { newRating ->
                    onRatingChanged(newRating)
                }
            )
        }
        Box(modifier = Modifier.weight(1f))
    }
}
