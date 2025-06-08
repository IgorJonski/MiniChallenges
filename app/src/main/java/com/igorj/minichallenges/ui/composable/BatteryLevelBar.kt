package com.igorj.minichallenges.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.igorj.minichallenges.ui.theme.Green
import com.igorj.minichallenges.ui.theme.Red
import com.igorj.minichallenges.ui.theme.SurfaceHigh
import com.igorj.minichallenges.ui.theme.Yellow
import com.igorj.minichallenges.ui.util.BatteryShape

@Composable
fun BatteryLevelBar(
    modifier: Modifier = Modifier,
    level: Float,
    sections: Int = 5,
    cornerRadius: Dp = 14.dp,
    nippleWidth: Dp = 4.dp,
    nippleHeight: Dp = 18.dp,
    borderThickness: Dp = 3.dp,
) {
    val batteryLevel = level.coerceIn(0f, 1f)
    val batteryShape = BatteryShape(
        cornerRadius = cornerRadius,
        nippleWidth = nippleWidth,
        nippleHeight = nippleHeight
    )
    val batteryLevelColor = when (batteryLevel) {
        in 0.0f..0.2f -> Red
        in 0.2f..<0.8f -> Yellow
        else -> Green
    }

    Box(
        modifier = modifier
            .clip(batteryShape)
            .background(SurfaceHigh)
            .padding(end = nippleWidth)
            .padding(borderThickness)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius - borderThickness))
                .background(SurfaceHigh)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = batteryLevel)
                    .clip(RoundedCornerShape(cornerRadius - borderThickness))
                    .background(batteryLevelColor)
            )

            Row(modifier = Modifier.fillMaxSize()) {
                repeat(sections) { index ->
                    Spacer(modifier = Modifier.weight(1f))
                    if (index < sections - 1) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(borderThickness)
                                .background(Color.White.copy(alpha = 0.5f))
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFE0E0E0)
@Composable
fun BatteryIndicatorPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        val level1 = 0.5f
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BatteryLevelBar(
                modifier = Modifier.size(width = 200.dp, height = 60.dp),
                level = level1,
            )
        }

        val level2 = 0.15f
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BatteryLevelBar(
                modifier = Modifier.size(width = 200.dp, height = 60.dp),
                level = level2,
            )
        }

        val level3 = 1.0f
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BatteryLevelBar(
                modifier = Modifier.size(width = 200.dp, height = 60.dp),
                level = level3,
            )
        }
    }
}
