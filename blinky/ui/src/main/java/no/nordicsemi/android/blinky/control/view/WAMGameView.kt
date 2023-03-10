package no.nordicsemi.android.blinky.control.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.nordicsemi.android.blinky.control.R
import no.nordicsemi.android.blinky.spec.GameData
import no.nordicsemi.android.common.theme.NordicTheme

@Composable
internal fun WAMGameControlView(state: GameData, modifier: Modifier = Modifier)
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
                    text = "Player score: ",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = state.totalScore.toString(),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Player name: ",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = state.msg,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Target time: ",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = state.targetTime.toString() + " ms",
                )
            }
        }
    }
}

@Composable
@Preview
private fun WAMGameControlViewPreview() {
    NordicTheme {
        WAMGameControlView(
            state = GameData("John Smit", 2),
            modifier = Modifier.padding(16.dp),
        )
    }
}