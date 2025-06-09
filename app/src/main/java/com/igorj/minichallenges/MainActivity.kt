package com.igorj.minichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.igorj.minichallenges.ui.composable.DawnDuskScreen
import com.igorj.minichallenges.ui.theme.LocalThemeConfig
import com.igorj.minichallenges.ui.theme.MiniChallengesTheme

class MainActivity : ComponentActivity() {

    private var currentRating by mutableIntStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (savedInstanceState != null) {
            currentRating = savedInstanceState.getInt("currentRating")
        }

        setContent {
            MiniChallengesTheme {
                val themeConfig = LocalThemeConfig.current

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(themeConfig.backgroundGradient)
                    )
                    DawnDuskScreen(
                        modifier = Modifier.padding(innerPadding),
                        currentRating = currentRating,
                        onRatingChanged = { newRating ->
                            currentRating = newRating
                        }
                    )
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentRating", currentRating)
    }
}
