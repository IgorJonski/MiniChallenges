package com.igorj.minichallenges.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.igorj.minichallenges.model.ThousandsSeparatorOption
import com.igorj.minichallenges.ui.theme.ComponentBackgroundColor
import com.igorj.minichallenges.ui.theme.SelectorBackgroundColor

@Composable
fun ThousandsSeparator(modifier: Modifier = Modifier) {

    val selectedOption = remember { mutableStateOf(ThousandsSeparatorOption.Dot) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(ComponentBackgroundColor)
            .padding(8.dp)
    ) {
        Text(
            text = "Thousands separator",
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(percent = 25))
                .background(SelectorBackgroundColor)
                .padding(4.dp)
        ) {
            ThousandsSeparatorOption.entries.forEach { option ->
                ThousandsSeparatorButton(
                    modifier = Modifier.weight(1f),
                    text = option.label,
                    isSelected = option == selectedOption.value,
                    onClick = {
                        selectedOption.value = option
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun ThousandsSeparatorPreview() {
    ThousandsSeparator()
}