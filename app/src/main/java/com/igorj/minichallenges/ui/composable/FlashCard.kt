package com.igorj.minichallenges.ui.composable

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.igorj.minichallenges.ui.extension.noRippleClickable
import com.igorj.minichallenges.ui.model.BackCardData
import com.igorj.minichallenges.ui.model.FrontCardData

@Composable
fun FlashCard(
    modifier: Modifier = Modifier,
    cutOffCornersSize: Dp = 32.dp,
    frontCardData: FrontCardData,
    backCardData: BackCardData
) {
    var flashCardFace by remember {
        mutableStateOf(FlashCardFace.Front)
    }

    val rotation by animateFloatAsState(
        targetValue = if (flashCardFace == FlashCardFace.Front) 0f else 180f,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing,
        ),
    )

    var cardHeight by remember { mutableStateOf(CARD_MIN_HEIGHT) }

    Card(
        modifier = modifier
            .noRippleClickable {
                flashCardFace = if (flashCardFace == FlashCardFace.Front) {
                    FlashCardFace.Back
                } else {
                    FlashCardFace.Front
                }
            }
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = null,
    ) {
        if (rotation <= 90f) {
            Box {
                FlashCardBackSide(
                    modifier = Modifier
                        /*
                        Invisible back side only to make front side
                         */
                        .alpha(0f),
                    cutOffCornersSize = cutOffCornersSize,
                    backCardData = backCardData,
                    onHeightCalculated = {
                        if (cardHeight < it) {
                            cardHeight = it
                        }
                    }
                )
                FlashCardFrontSide(
                    modifier = Modifier.heightIn(min = cardHeight),
                    cutOffCornersSize = cutOffCornersSize,
                    frontCardData = frontCardData,
                )
            }
        } else {
            Box(modifier = Modifier.graphicsLayer { rotationY = 180f }) {
                FlashCardBackSide(
                    modifier = Modifier.heightIn(min = cardHeight),
                    cutOffCornersSize = cutOffCornersSize,
                    backCardData = backCardData
                )
            }
        }
    }
}

enum class FlashCardFace {
    Front, Back
}

private val CARD_MIN_HEIGHT = 380.dp

@Preview
@Composable
private fun FlashCardPreview() {
    val crew = listOf(
        "Oleg Kononenko", "Nikolai Chub", "Tracy Caldwell Dyson",
        "Matthew Dominick", "Michael Barratt", "Jeanette Epps",
        "Alexander Grebenkin", "Butch Wilmore", "Sunita Williams",
        "Li Guangsu", "Li Cong", "Ye Guangfu"
    )

    FlashCard(
        modifier = Modifier.height(300.dp),
        frontCardData = FrontCardData(
            craftName = "ISS Spacecraft",
            numberOfCrew = 12
        ),
        backCardData = BackCardData(crewMembers = crew),
    )
}