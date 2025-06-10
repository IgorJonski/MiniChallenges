package com.igorj.minichallenges.ui.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class CutOffCornersShape(
    private val cutCornerSize: Dp,
    private val type: CutOffCornersShapeType
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val cutSizePx = with(density) { cutCornerSize.toPx() }
        val path = Path()

        when (type) {
            CutOffCornersShapeType.TopRightAndBottomLeftCutOff -> {
                path.moveTo(0f, 0f)
                path.lineTo(size.width - cutSizePx, 0f)
                path.lineTo(size.width, cutSizePx)
                path.lineTo(size.width, size.height)
                path.lineTo(cutSizePx, size.height)
                path.lineTo(0f, size.height - cutSizePx)
                path.close()
            }

            CutOffCornersShapeType.TopLeftAndBottomRightCutOff -> {
                path.moveTo(cutSizePx, 0f)
                path.lineTo(size.width, 0f)
                path.lineTo(size.width, size.height - cutSizePx)
                path.lineTo(size.width - cutSizePx, size.height)
                path.lineTo(0f, size.height)
                path.lineTo(0f, cutSizePx)
                path.close()
            }
        }
        return Outline.Generic(path)
    }
}

enum class CutOffCornersShapeType {
    TopRightAndBottomLeftCutOff,
    TopLeftAndBottomRightCutOff
}

