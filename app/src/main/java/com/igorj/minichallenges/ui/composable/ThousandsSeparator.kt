package com.igorj.minichallenges.ui.composable

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.igorj.minichallenges.model.ThousandsSeparatorOption
import com.igorj.minichallenges.ui.theme.ComponentBackgroundColor
import com.igorj.minichallenges.ui.theme.SelectorBackgroundColor
import kotlin.math.roundToInt

@Composable
fun ThousandsSeparator(
    modifier: Modifier = Modifier,
    selectedOption: ThousandsSeparatorOption,
    onOptionSelected: (ThousandsSeparatorOption) -> Unit,
) {
    var rowWeightPx by remember { mutableFloatStateOf(0f) }
    var rowHeightPx by remember { mutableFloatStateOf(0f) }

    val selectedOptionIndicatorWidthPx = rowWeightPx / ThousandsSeparatorOption.entries.size
    val selectedOptionIndicatorOffsetX = remember { Animatable(0f) }

    LaunchedEffect(selectedOption, rowWeightPx) {
        if (rowWeightPx > 0f) {
            val targetOffsetX = selectedOption.ordinal * selectedOptionIndicatorWidthPx
            selectedOptionIndicatorOffsetX.animateTo(
                targetValue = targetOffsetX,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(ComponentBackgroundColor)
            .padding(8.dp),
    ) {
        Text(
            text = "Thousands separator",
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(percent = 25))
                .background(SelectorBackgroundColor)
                .padding(4.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            val selectionIndicatorHeightDp = with(LocalDensity.current) { rowHeightPx.toDp() }

            Box(
                modifier = Modifier
                    .fillMaxWidth(1f / ThousandsSeparatorOption.entries.size)
                    .offset {
                        IntOffset(x = selectedOptionIndicatorOffsetX.value.roundToInt(), y = 0)
                    }
                    .height(selectionIndicatorHeightDp)
                    .clip(RoundedCornerShape(percent = 25))
                    .background(Color.White)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged { size ->
                        rowHeightPx = size.height.toFloat()
                        rowWeightPx = size.width.toFloat()
                    }
            ) {
                ThousandsSeparatorOption.entries.forEach { option ->
                    ThousandsSeparatorButton(
                        text = option.label,
                        isSelected = selectedOption == option,
                        onClick = {
                            onOptionSelected(option)
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ThousandsSeparatorPreview() {
    val selectedOption = remember { mutableStateOf(ThousandsSeparatorOption.Dot) }
    ThousandsSeparator(
        selectedOption = selectedOption.value,
        onOptionSelected = { newOption ->
            selectedOption.value = newOption
        },
    )
}