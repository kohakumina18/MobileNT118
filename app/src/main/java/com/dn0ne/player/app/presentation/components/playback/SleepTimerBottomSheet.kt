package com.dn0ne.player.app.presentation.components.playback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dn0ne.player.R
import com.dn0ne.player.SleepTimer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SleepTimerBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest
    ) {
        val context = LocalContext.current
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(horizontal = 28.dp)
                .padding(bottom = 28.dp)
        ) {
            val minutesLeft by SleepTimer.minutesLeft.collectAsState()
            val isRunning by SleepTimer.isRunning.collectAsState()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Timer,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = context.resources.getString(R.string.sleep_timer) +
                                " ($minutesLeft " +
                                context.resources.getString(R.string.short_minutes) +
                                ")",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                FilledTonalButton(
                    onClick = {
                        if (isRunning) {
                            SleepTimer.stop()
                        } else SleepTimer.start()
                    }
                ) {
                    Text(
                        text = if (isRunning) {
                            context.resources.getString(R.string.stop_timer)
                        } else context.resources.getString(R.string.start_timer)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Slider(
                value = minutesLeft.toFloat(),
                onValueChange = {
                    SleepTimer.updateMinutesLeft(it.toInt())
                },
                valueRange = 1f..120f
            )
        }
    }
}