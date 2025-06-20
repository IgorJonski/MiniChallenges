package com.igorj.minichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.igorj.minichallenges.ui.composable.FlashCard
import com.igorj.minichallenges.ui.model.BackCardData
import com.igorj.minichallenges.ui.model.FlashCardState
import com.igorj.minichallenges.ui.model.FrontCardData
import com.igorj.minichallenges.ui.theme.BackgroundGradient
import com.igorj.minichallenges.ui.theme.MiniChallengesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniChallengesTheme {

                val flashCardViewModel: FlashCardViewModel = viewModel()
                val uiState = flashCardViewModel.uiState.collectAsStateWithLifecycle()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
                        when (val state = uiState.value) {
                            is FlashCardState.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(48.dp),
                                    color = Color.White,
                                    strokeWidth = 4.dp,
                                    strokeCap = StrokeCap.Square
                                )
                            }
                            is FlashCardState.Error -> {
                                Text(
                                    text = "Failed to load data:\n${state.message}",
                                    color = Color.Red,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                            is FlashCardState.Success -> {
                                val flashCards = state.data
                                if (flashCards.isEmpty()) {
                                    Text(
                                        text = "No flash cards available.",
                                        color = Color.White
                                    )
                                } else {
                                    val pagerState = rememberPagerState(pageCount = { flashCards.size })

                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        HorizontalPager(
                                            state = pagerState,
                                            modifier = Modifier.weight(1f),
                                            contentPadding = PaddingValues(horizontal = 36.dp),
                                            pageSpacing = 16.dp
                                        ) { pageIndex ->
                                            val spacecraft = flashCards[pageIndex]
                                            FlashCard(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .align(Alignment.CenterHorizontally),
                                                frontCardData = FrontCardData(
                                                    craftName = spacecraft.craftName,
                                                    numberOfCrew = spacecraft.crewCount
                                                ),
                                                backCardData = BackCardData(
                                                    crewMembers = spacecraft.crew
                                                )
                                            )
                                        }

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            repeat(pagerState.pageCount) { iteration ->
                                                val (color, size) = if (iteration == pagerState.currentPage) {
                                                    Color.White to 14.dp
                                                } else {
                                                    Color.LightGray to 12.dp
                                                }
                                                Box(
                                                    modifier = Modifier
                                                        .padding(4.dp)
                                                        .clip(CircleShape)
                                                        .background(color)
                                                        .size(size)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
