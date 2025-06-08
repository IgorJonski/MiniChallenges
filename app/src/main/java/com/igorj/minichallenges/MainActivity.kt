package com.igorj.minichallenges

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.igorj.minichallenges.ui.composable.BatteryLevelIndicator
import com.igorj.minichallenges.ui.theme.MiniChallengesTheme
import com.igorj.minichallenges.ui.theme.Surface
import com.igorj.minichallenges.util.BatteryLevelReceiver

class MainActivity : ComponentActivity() {

    private var batteryLevel by mutableFloatStateOf(0f)

    private val batteryLevelReceiver = BatteryLevelReceiver(
        onBatteryLevelChanged = { level ->
            Log.d("LOGCAT", "Battery level changed: $level")
            batteryLevel = level
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MiniChallengesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Surface,
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Center
                    ) {
                        val animatedBatteryLevel = remember { Animatable(0f) }

                        LaunchedEffect(batteryLevel) {
                            animatedBatteryLevel.animateTo(
                                targetValue = batteryLevel,
                                animationSpec = tween(
                                    durationMillis = 500,
                                    easing = FastOutSlowInEasing
                                )
                            )
                        }

                        BatteryLevelIndicator(
                            modifier = Modifier.padding(
                                vertical = 16.dp,
                                horizontal = 24.dp
                            ),
                            level = animatedBatteryLevel.value
                        )
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LOGCAT", "Registering battery level receiver")
        ContextCompat.registerReceiver(
            applicationContext,
            batteryLevelReceiver,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED),
            ContextCompat.RECEIVER_EXPORTED
        )
    }

    override fun onStop() {
        super.onStop()
        Log.d("LOGCAT", "Unregistering battery level receiver")
        applicationContext.unregisterReceiver(batteryLevelReceiver)
    }
}
