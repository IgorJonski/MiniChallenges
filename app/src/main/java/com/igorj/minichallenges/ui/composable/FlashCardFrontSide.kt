package com.igorj.minichallenges.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.minichallenges.R
import com.igorj.minichallenges.ui.model.FrontCardData
import com.igorj.minichallenges.ui.shape.CutOffCornersShape
import com.igorj.minichallenges.ui.shape.CutOffCornersShapeType
import com.igorj.minichallenges.ui.theme.FrontSideArrow
import com.igorj.minichallenges.ui.theme.FrontSideColor
import com.igorj.minichallenges.ui.theme.FrontSidePrimaryText
import com.igorj.minichallenges.ui.theme.FrontSideSecondaryText

@Composable
fun FlashCardFrontSide(
    modifier: Modifier = Modifier,
    cutOffCornersSize: Dp = 32.dp,
    frontCardData: FrontCardData,
) {
    val frontShape = remember(cutOffCornersSize) {
        CutOffCornersShape(cutOffCornersSize, CutOffCornersShapeType.TopRightAndBottomLeftCutOff)
    }

    Card(
        modifier = modifier.aspectRatio(FLASH_CARD_RATIO),
        shape = frontShape,
        colors = CardDefaults.cardColors(containerColor = FrontSideColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.ic_border),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 32.dp)
                    .align(Alignment.BottomEnd),
                painter = painterResource(R.drawable.ic_rocket),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .align(Alignment.TopStart),
                painter = painterResource(R.drawable.ic_flip),
                contentDescription = null,
                tint = FrontSideArrow,
            )
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = frontCardData.craftName,
                    color = FrontSidePrimaryText,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${frontCardData.numberOfCrew} crew members",
                    color = FrontSideSecondaryText,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF222222)
@Composable
fun FlashCardFrontSidePreview() {
    FlashCardFrontSide(
        modifier = Modifier.width(310.dp),
        frontCardData = FrontCardData(
            craftName = "ISS Spacecraft",
            numberOfCrew = 12
        ),
    )
}
