package com.igorj.minichallenges.ui.model

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.igorj.minichallenges.ui.theme.DarkBackgroundEnd
import com.igorj.minichallenges.ui.theme.DarkBackgroundStart
import com.igorj.minichallenges.ui.theme.DarkCard
import com.igorj.minichallenges.ui.theme.DarkStarInactive
import com.igorj.minichallenges.ui.theme.DarkText
import com.igorj.minichallenges.ui.theme.LightBackgroundEnd
import com.igorj.minichallenges.ui.theme.LightBackgroundStart
import com.igorj.minichallenges.ui.theme.LightCard
import com.igorj.minichallenges.ui.theme.LightStarInactive
import com.igorj.minichallenges.ui.theme.LightText
import com.igorj.minichallenges.ui.theme.StarActive

sealed class ThemeConfig(
    val backgroundGradient: Brush,
    val cardColor: Color,
    val starActiveColor: Color,
    val starInactiveColor: Color,
    val textColor: Color,
) {
    data object DarkMode: ThemeConfig(
        backgroundGradient = Brush.linearGradient(
            colors = listOf(DarkBackgroundEnd, DarkBackgroundStart)
        ),
        cardColor = DarkCard,
        starActiveColor = StarActive,
        starInactiveColor = DarkStarInactive,
        textColor = DarkText
    )
    data object LightMode: ThemeConfig(
        backgroundGradient = Brush.linearGradient(
            colors = listOf(LightBackgroundStart, LightBackgroundEnd)
        ),
        cardColor = LightCard,
        starActiveColor = StarActive,
        starInactiveColor = LightStarInactive,
        textColor = LightText
    )
}