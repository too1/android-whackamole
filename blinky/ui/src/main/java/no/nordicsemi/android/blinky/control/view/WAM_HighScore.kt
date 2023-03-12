package no.nordicsemi.android.blinky.control.view

import android.widget.ListView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
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
import no.nordicsemi.android.blinky.spec.GameHighscoreEntry
import no.nordicsemi.android.common.theme.NordicTheme

@Composable
internal fun createHighscoreEntryView(highscore: GameHighscoreEntry) {
    Box(modifier = Modifier.padding(2.dp)) {
        Box(
            modifier = Modifier
                .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
                .background(
                    color = colorGood,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(4.dp),

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = highscore.name,
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    style = MaterialTheme.typography.labelMedium,
                )

                Text(
                    text = "Score: " + highscore.score.toString(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                )

                Text(
                    text = "Best time: " + highscore.minTime.toString() + " ms",
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                )
            }

        }
    }
}
@Composable
internal fun WAM_HighScoreView(
    state: GameData,
    modifier: Modifier = Modifier,
) {
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
                Text(
                    text = "High Score",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                )
            }
            state.highscores.forEach { highscore ->
                createHighscoreEntryView(highscore)
            }
        }
    }
}

@Composable
@Preview
private fun WAM_HighScoreViewPreview() {
    NordicTheme {
        WAM_HighScoreView(
            state = GameData("ball", 12),
            modifier = Modifier.padding(16.dp),
        )
    }
}