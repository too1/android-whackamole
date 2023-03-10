package no.nordicsemi.android.blinky.ble.data

import android.bluetooth.BluetoothDevice
import no.nordicsemi.android.blinky.spec.GameData
import no.nordicsemi.android.blinky.spec.GameInstData

// Use global variables for persistant storage (surely a better way exists....)
var totalScore: Int = 0
var currentRound: Int = 0

class ButtonState: ButtonCallback() {
    var buttonState: GameData = GameData("none", 0)
    var gameInstState: GameInstData = GameInstData("yep", -2)

    override fun onButtonStateChanged(device: BluetoothDevice, state: GameData) {
        // Update score from persistent variables
        totalScore += state.pointIncrement
        state.totalScore = totalScore
        // Update current round from persistent variables
        if (state.roundNumber > 0) {
            currentRound = state.roundNumber
        }
        else {
            state.roundNumber = currentRound
        }
        // Set the state
        this.buttonState = state
    }

    override fun onGameInstChanged(device: BluetoothDevice, state: GameInstData) {
        this.gameInstState = state
    }
}