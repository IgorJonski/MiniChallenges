package com.igorj.minichallenges.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.minichallenges.R
import com.igorj.minichallenges.ui.theme.LocalThemeConfig

@Composable
fun RatingSection(
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    currentRating: Int,
    onRatingChanged: (Int) -> Unit
) {
    val themeConfig = LocalThemeConfig.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.ratingSection_ui_howWasYourDayLabel),
            fontWeight = FontWeight.SemiBold,
            fontSize = 36.sp,
            color = themeConfig.textColor
        )
        RatingBar(
            maxRating = maxRating,
            currentRating = currentRating,
            onRatingChanged = onRatingChanged
        )
    }
}

@Preview
@Composable
private fun RatingSectionPreview() {
    var currentRating by remember { mutableIntStateOf(0) }

    RatingSection(
        currentRating = currentRating,
        onRatingChanged = {
            currentRating = it
        }
    )
}
