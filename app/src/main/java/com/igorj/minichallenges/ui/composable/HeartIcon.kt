package com.igorj.minichallenges.ui.composable

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.igorj.minichallenges.R
import com.igorj.minichallenges.ui.theme.SurfaceLow

@Composable
fun HeartIcon(
    modifier: Modifier = Modifier,
    isAnimating: Boolean = true,
) {
    if (isAnimating) {
        PulsatingHeart(modifier = modifier)
    } else {
        Icon(
            modifier = modifier,
            painter = painterResource(id = R.drawable.ic_inactive_heart),
            contentDescription = stringResource(R.string.batteryLevelIndicator_alt_heartIcon),
            tint = SurfaceLow
        )
    }
}

@Preview
@Composable
private fun HeartIconPreview() {
    HeartIcon(isAnimating = true)
}
