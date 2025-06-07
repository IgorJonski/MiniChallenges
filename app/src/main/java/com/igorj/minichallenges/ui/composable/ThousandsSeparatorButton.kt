package com.igorj.minichallenges.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igorj.minichallenges.ui.extension.noRippleClickable
import com.igorj.minichallenges.ui.theme.UnselectedTextColor

@Composable
fun ThousandsSeparatorButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .noRippleClickable(onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
            color = if (isSelected) Color.Black else UnselectedTextColor,
        )
    }
}

@Preview
@Composable
private fun ThousandsSeparatorButtonPreview() {
    var isSelected by remember { mutableStateOf(true) }

    ThousandsSeparatorButton(
        text = "1,000",
        isSelected = isSelected,
        onClick = {
            isSelected = !isSelected
        }
    )
}