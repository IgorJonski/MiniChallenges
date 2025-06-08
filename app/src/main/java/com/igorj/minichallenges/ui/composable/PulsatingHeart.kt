package com.igorj.minichallenges.ui.composable

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.igorj.minichallenges.R
import com.igorj.minichallenges.ui.theme.Red
import com.igorj.minichallenges.ui.theme.RedAlt

@Composable
fun PulsatingHeart(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200),
            repeatMode = RepeatMode.Reverse
        )
    )

    val color by infiniteTransition.animateColor(
        initialValue = RedAlt,
        targetValue = Red,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        modifier = modifier.scale(scale),
        painter = painterResource(id = R.drawable.ic_heart),
        contentDescription = stringResource(R.string.batteryLevelIndicator_alt_pulsatingHeart),
        colorFilter = ColorFilter.tint(color)
    )
}

@Preview
@Composable
private fun PulsatingHeartPreview() {
    PulsatingHeart()
}
