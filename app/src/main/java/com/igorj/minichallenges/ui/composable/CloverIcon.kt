package com.igorj.minichallenges.ui.composable

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.igorj.minichallenges.R
import com.igorj.minichallenges.ui.theme.Green
import com.igorj.minichallenges.ui.theme.SurfaceLow

@Composable
fun CloverIcon(
    modifier: Modifier = Modifier,
    isActive: Boolean = true,
) {
    Icon(
        modifier = modifier.then(
            if (!isActive) Modifier else Modifier.scale(1.2f)
        ),
        painter = painterResource(id = R.drawable.ic_clover),
        contentDescription = stringResource(R.string.batteryLevelIndicator_alt_cloverIcon),
        tint = if (!isActive) SurfaceLow else Green
    )
}

@Preview
@Composable
private fun CloverIconPreview() {
    CloverIcon(isActive = false)
}
