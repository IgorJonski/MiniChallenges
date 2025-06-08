package com.igorj.minichallenges.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class BatteryShape(
    private val cornerRadius: Dp,
    private val nippleWidth: Dp,
    private val nippleHeight: Dp
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }
        val nippleWidthPx = with(density) { nippleWidth.toPx() }
        val nippleHeightPx = with(density) { nippleHeight.toPx() }

        val mainCompartmentWidth = size.width - nippleWidthPx

        val path = Path().apply {
            addRoundRect(
                RoundRect(
                    left = 0f,
                    top = 0f,
                    right = mainCompartmentWidth,
                    bottom = size.height,
                    cornerRadius = CornerRadius(cornerRadiusPx)
                )
            )
            addRoundRect(
                RoundRect(
                    left = mainCompartmentWidth - NIPPLE_OFFSET,
                    top = (size.height - nippleHeightPx) / 2f,
                    right = size.width,
                    bottom = (size.height + nippleHeightPx) / 2f,
                    cornerRadius = CornerRadius(cornerRadiusPx / 2)
                )
            )
        }
        return Outline.Generic(path)
    }

    private companion object {
        const val NIPPLE_OFFSET = 20f
    }
}

@Preview
@Composable
private fun BatteryShapePreview() {
    Box(
        modifier = Modifier
            .size(100.dp, 50.dp)
            .clip(
                BatteryShape(
                    cornerRadius = 8.dp,
                    nippleWidth = 4.dp,
                    nippleHeight = 18.dp
                )
            )
            .background(Color.White)
    )
}
