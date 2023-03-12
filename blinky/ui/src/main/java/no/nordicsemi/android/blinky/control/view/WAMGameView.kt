package no.nordicsemi.android.blinky.control.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.nordicsemi.android.blinky.control.R
import no.nordicsemi.android.blinky.spec.GameChallenge
import no.nordicsemi.android.blinky.spec.GameData
import no.nordicsemi.android.common.theme.NordicTheme
val colorGood = Color.hsl(90.0f, 0.8f, 0.8f)
val colorBad = Color.hsl(0.0f, 0.8f, 0.8f)
val colorGoodS = Color.hsl(90.0f, 0.8f, 0.3f)
val colorBadS = Color.hsl(0.0f, 0.8f, 0.3f)

@Composable
internal fun createChallengeView(challenge: GameChallenge)
{
    Box(modifier = Modifier.padding(2.dp)) {
        Box(
            modifier = Modifier
                .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
                .background(
                    color = if (challenge.success) colorGood else colorBad,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(4.dp),

            ) {
            if (challenge.success) {
                Column(
                    modifier = Modifier
                        .padding(0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Text(
                            text = "ChgNo",
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp),
                            style = MaterialTheme.typography.labelMedium,
                        )

                        Text(
                            text = challenge.time.toString() + " / " + challenge.target.toString() + " ms",
                            modifier = Modifier
                                .weight(3f)
                                .padding(4.dp),
                        )

                        Text(
                            text = "Fouls",
                            modifier = Modifier
                                .weight(1.4f)
                                .padding(4.dp),
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 0.dp),
                    ) {

                        Text(
                            text = challenge.index.toString() + "",
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                        Slider(
                            value = challenge.time.toFloat() / challenge.target.toFloat(),
                            onValueChange = {},
                            valueRange = 0f..1f,
                            modifier = Modifier.weight(3f),
                        )
                        Text(
                            text = challenge.fouls.toString(),
                            modifier = Modifier
                                .weight(1.4f)
                                .padding(4.dp),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .padding(0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Text(
                            text = "ChgNo",
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp),
                            style = MaterialTheme.typography.labelMedium,
                        )

                        Text(
                            text = challenge.time.toString() + " / " + challenge.target.toString() + " ms",
                            modifier = Modifier
                                .weight(3f)
                                .padding(4.dp),
                        )

                        Text(
                            text = "Fouls",
                            modifier = Modifier
                                .weight(1.4f)
                                .padding(4.dp),
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 0.dp),
                    ) {

                        Text(
                            text = challenge.index.toString() + "",
                            modifier = Modifier
                                .weight(4f)
                                .padding(4.dp),
                            style = MaterialTheme.typography.headlineMedium,
                        )

                        Text(
                            text = challenge.fouls.toString(),
                            modifier = Modifier
                                .weight(1.4f)
                                .padding(4.dp),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
            }
        }
    }
}

@Composable
internal fun WAMGameControlView(state: GameData, altState: String, modifier: Modifier = Modifier)
{
    OutlinedCard(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "nRF Whack-A-Mole",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Player - " + state.playerName,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Points",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    text = "Total",
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = "Fouls",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = state.totalPoints.toString(),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    color = colorGoodS,
                )
                Text(
                    text = state.totalScore.toString(),
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                    color = if (state.totalScore >= 0) colorGoodS else colorBadS,
                )
                Text(
                    text = state.totalFouls.toString(),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    color = colorBadS,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Round: " + state.roundNumber.toString() + "/6",
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Slider(
                    value = state.roundNumber.toFloat(),
                    onValueChange = {},
                    valueRange = 0f..6f,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Completed challenges: ",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = state.numChallenges.toString() + "",
                )
            }
            state.challenges.forEach { challenge ->
                createChallengeView(challenge)
            }
            /*
            if(state.challenges.isNotEmpty()) {
                LazyColumn {
                    state.challenges.forEach { challenge ->
                        item {
                            createChallengeView(challenge)
                        }
                    }
                }
            }*/
        }

    }
}

@Composable
@Preview
private fun WAMGameControlViewPreview() {
    NordicTheme {
        WAMGameControlView(
            state = GameData("", 2),
            altState = "Yoda",
            modifier = Modifier.padding(16.dp),
        )
    }
}