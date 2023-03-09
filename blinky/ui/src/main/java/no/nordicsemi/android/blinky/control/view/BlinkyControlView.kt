package no.nordicsemi.android.blinky.control.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.nordicsemi.android.common.theme.NordicTheme

@Composable
internal fun BlinkyControlView(
    ledState: String,
    buttonState: String,
    alternateState: String,
    onStateChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        LedControlView(
            state = true,
            onStateChanged = onStateChanged,
        )

        ButtonControlView(
            state = buttonState
        )

        WAMGameControlView(
            state = alternateState
        )
    }
}

@Preview
@Composable
private fun BlinkyControlViewPreview() {
    NordicTheme {
        BlinkyControlView(
            ledState = "Empty",
            buttonState = "Empty",
            alternateState = "Alt",
            onStateChanged = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}