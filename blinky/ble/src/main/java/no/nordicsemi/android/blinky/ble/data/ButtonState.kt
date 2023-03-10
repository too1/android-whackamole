package no.nordicsemi.android.blinky.ble.data

import android.bluetooth.BluetoothDevice
import no.nordicsemi.android.blinky.spec.GameData

class ButtonState: ButtonCallback() {
    var buttonState: GameData = GameData("none", 0)
    var gameState: String = "gamestate"

    override fun onButtonStateChanged(device: BluetoothDevice, state: GameData) {
        this.buttonState = state
    }

    override fun onGameStateChanged(device: BluetoothDevice, state: String) {
        this.gameState = state
    }
}