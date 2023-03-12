package no.nordicsemi.android.blinky.control.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
    Box(modifier = Modifier
        .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
        .background(if (challenge.success) colorGood else colorBad)
        .padding(4.dp),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 0.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = challenge.time.toString() + " ms",
                modifier = Modifier.weight(if (challenge.success) 1f else 4f)
                    .padding(4.dp)
            )
            if (challenge.success) {
                Slider(
                    value = challenge.time.toFloat() / challenge.target.toFloat(),
                    onValueChange = {},
                    valueRange = 0f..1f,
                    modifier = Modifier.weight(3f),
                )
            }
            Text(
                text = challenge.target.toString() + " ms",
                modifier = Modifier.weight(1f).padding(4.dp),
            )
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
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    imageVector = Icons.Default.RadioButtonChecked,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 16.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                )
                Text(
                    text = "Running Game",
                    style = MaterialTheme.typography.headlineMedium,
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
                    text = "TOTAL",
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
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
                    style = MaterialTheme.typography.headlineMedium,
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
            /*
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Challenge number: 1/6",
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
                    value = 0.7f,
                    onValueChange = {},
                    valueRange = 0f..1f,
                )
            }
            */
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