package com.igorj.minichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.igorj.minichallenges.ui.composable.FlashCard
import com.igorj.minichallenges.ui.model.BackCardData
import com.igorj.minichallenges.ui.model.FrontCardData
import com.igorj.minichallenges.ui.theme.BackgroundGradient
import com.igorj.minichallenges.ui.theme.MiniChallengesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniChallengesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val crew = listOf(
                        "Oleg Kononenko", "Nikolai Chub", "Tracy Caldwell Dyson",
                        "Matthew Dominick", "Michael Barratt", "Jeanette Epps",
                        "Alexander Grebenkin", "Butch Wilmore", "Sunita Williams",
                        "Li Guangsu", "Li Cong", "Ye Guangfu"
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(BackgroundGradient)
                    )
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        FlashCard(
                            modifier = Modifier.width(310.dp),
                            frontCardData = FrontCardData(
                                craftName = "ISS Spacecraft",
                                numberOfCrew = 12
                            ),
                            backCardData = BackCardData(crewMembers = crew),
                        )
                    }
                }
            }
        }
    }
}
