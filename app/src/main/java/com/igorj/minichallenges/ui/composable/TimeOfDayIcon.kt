package com.igorj.minichallenges.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.igorj.minichallenges.R

@Composable
fun TimeOfDayIcon(
    modifier: Modifier = Modifier
) {
    val isDarkMode = isSystemInDarkTheme()

    val (icon, contentDescription) = if (isDarkMode) {
        R.drawable.ic_moon to R.string.timeOfDayIcon_alt_moonIcon
    } else {
        R.drawable.ic_sun to R.string.timeOfDayIcon_alt_sunIcon
    }

    Image(
        modifier = modifier,
        painter = painterResource(id = icon),
        contentDescription = stringResource(id = contentDescription)
    )
}

@Preview
@Composable
private fun TimeOfDayIconPreview() {
    TimeOfDayIcon()
}