package com.igorj.minichallenges.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BatteryLevelIndicator(
    modifier: Modifier = Modifier,
    level: Float
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeartIcon(
            modifier = Modifier.size(40.dp),
            isAnimating = level <= 0.2f
        )
        BatteryLevelBar(
            modifier = Modifier
                .height(80.dp)
                .weight(1f),
            level = level,
            cornerRadius = 20.dp,
            nippleHeight = 26.dp,
            nippleWidth = 6.dp
        )
        CloverIcon(
            modifier = Modifier.size(40.dp),
            isActive = level >= 0.8f
        )
    }
}

@Preview
@Composable
private fun BatteryLevelIndicatorPreview() {
    BatteryLevelIndicator(
        level = 0.8f,
    )
}
