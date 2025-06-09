package com.igorj.minichallenges.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igorj.minichallenges.R
import com.igorj.minichallenges.ui.extension.noRippleClickable
import com.igorj.minichallenges.ui.theme.LocalThemeConfig

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    currentRating: Int,
    onRatingChanged: (Int) -> Unit
) {
    val themeConfig = LocalThemeConfig.current

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(percent = 100))
            .background(themeConfig.cardColor)
            .padding(
                vertical = 12.dp,
                horizontal = 32.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxRating) { index ->
            Icon(
                modifier = Modifier
                    .noRippleClickable {
                        onRatingChanged(index + 1)
                    }
                    .aspectRatio(1f)
                    .weight(1f)
                    .padding(4.dp),
                painter = painterResource(R.drawable.ic_star),
                contentDescription = stringResource(R.string.ratingBar_alt_starIcon),
                tint = if (index < currentRating) {
                    themeConfig.starActiveColor
                } else {
                    themeConfig.starInactiveColor
                }
            )
        }
    }
}

@Preview
@Composable
private fun RatingBarPreview() {
    var currentRating by remember { mutableIntStateOf(0) }

    RatingBar(
        currentRating = currentRating,
        onRatingChanged = {
            currentRating = it
        }
    )
}