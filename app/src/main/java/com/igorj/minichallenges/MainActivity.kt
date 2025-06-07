package com.igorj.minichallenges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.igorj.minichallenges.model.ThousandsSeparatorOption
import com.igorj.minichallenges.ui.composable.ThousandsSeparator
import com.igorj.minichallenges.ui.theme.MiniChallengesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniChallengesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val selectedOption = remember { mutableStateOf(ThousandsSeparatorOption.Dot) }

                    ThousandsSeparator(
                        modifier = Modifier.padding(innerPadding),
                        selectedOption = selectedOption.value,
                        onOptionSelected = { newOption ->
                            selectedOption.value = newOption
                        }
                    )
                }
            }
        }
    }
}