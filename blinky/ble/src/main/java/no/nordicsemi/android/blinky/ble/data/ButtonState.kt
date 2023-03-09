package no.nordicsemi.android.blinky.ble.data

import android.bluetooth.BluetoothDevice

class ButtonState: ButtonCallback() {
    var state: String = "none"
    var gameState: String = "gamestate"

    override fun onButtonStateChanged(device: BluetoothDevice, state: String) {
        this.state = state
    }

    override fun onGameStateChanged(device: BluetoothDevice, state: String) {
        this.gameState = state
    }
}