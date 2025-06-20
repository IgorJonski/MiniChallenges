package com.igorj.minichallenges.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.minichallenges.R
import com.igorj.minichallenges.ui.model.BackCardData
import com.igorj.minichallenges.ui.shape.CutOffCornersShape
import com.igorj.minichallenges.ui.shape.CutOffCornersShapeType
import com.igorj.minichallenges.ui.theme.BackSideArrow
import com.igorj.minichallenges.ui.theme.BackSideColor
import com.igorj.minichallenges.ui.theme.BackSideText

@Composable
fun FlashCardBackSide(
    modifier: Modifier = Modifier,
    cutOffCornersSize: Dp = 32.dp,
    backCardData: BackCardData,
    onHeightCalculated: (Dp) -> Unit = { _ -> }
) {
    val backShape = remember(cutOffCornersSize) {
        CutOffCornersShape(cutOffCornersSize, CutOffCornersShapeType.TopLeftAndBottomRightCutOff)
    }

    val density = LocalDensity.current

    Card(
        modifier = modifier
            .onGloballyPositioned {
                val height = with(density) { it.size.height.toDp() }
                onHeightCalculated(height)
            },
        shape = backShape,
        colors = CardDefaults.cardColors(containerColor = BackSideColor)
    ) {
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .graphicsLayer {
                        rotationX = 180f
                    },
                painter = painterResource(R.drawable.ic_border),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )

            Image(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(Alignment.CenterStart)
                    .graphicsLayer {
                        rotationY = 180f
                    },
                painter = painterResource(R.drawable.ic_rocket),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
            )
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd),
                painter = painterResource(R.drawable.ic_flip),
                contentDescription = null,
                tint = BackSideArrow
            )

            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                backCardData.crewMembers.forEachIndexed { index, name ->
                    Text(
                        text = "${index + 1}. $name",
                        color = BackSideText,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF222222)
@Composable
fun FlashCardBackSidePreview() {
    val crew = listOf(
        "Oleg Kononenko", "Nikolai Chub", "Tracy Caldwell Dyson",
        "Matthew Dominick", "Michael Barratt", "Jeanette Epps",
        "Alexander Grebenkin", "Butch Wilmore", "Sunita Williams",
        "Li Guangsu", "Li Cong", "Ye Guangfu"
    )

    FlashCardBackSide(
        backCardData = BackCardData(crewMembers = crew),
    )
}
