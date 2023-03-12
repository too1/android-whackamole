package no.nordicsemi.android.blinky.ble.data

import android.bluetooth.BluetoothDevice
import no.nordicsemi.android.blinky.spec.GameChallenge
import no.nordicsemi.android.blinky.spec.GameData
import no.nordicsemi.android.blinky.spec.GameInstData

// Use global variables for persistant storage (surely a better way exists....)
var currentRound: Int = 0
var actualGameState: GameData = GameData("Cackness", 0)

class ButtonState: ButtonCallback() {
    var buttonState: GameData = GameData("none", 0)
    var gameInstState: GameInstData = GameInstData("yep", -2)

    override fun onButtonStateChanged(device: BluetoothDevice, state: GameData) {
        // Update score/points/fouls from persistent variables
        actualGameState.totalPoints += state.pointIncrement
        actualGameState.totalFouls += state.foulIncrement
        actualGameState.totalScore = actualGameState.totalPoints - actualGameState.totalFouls
        state.totalScore = actualGameState.totalScore
        state.totalPoints = actualGameState.totalPoints
        state.totalFouls = actualGameState.totalFouls
        // Update current round from persistent variables
        if (state.roundNumber > 0) {
            currentRound = state.roundNumber
        }
        else {
            state.roundNumber = currentRound
        }
        // Update challenge list from persistent variables
        if(state.startNewGame) {
            actualGameState.challenges.clear()
            actualGameState.numChallenges = 0
            actualGameState.totalPoints = 0
            actualGameState.totalScore = 0
            actualGameState.totalPoints = 0
        }
        while (state.challenges.isNotEmpty()) {
            var newChallenge = state.challenges.removeFirst()
            newChallenge.index = actualGameState.numChallenges
            actualGameState.numChallenges++
            actualGameState.challenges.add(newChallenge)
        }
        if(actualGameState.challenges.isNotEmpty()) {
            state.challenges = actualGameState.challenges.takeLast(6) as MutableList<GameChallenge>
            state.challenges.reverse()
            state.numChallenges = actualGameState.numChallenges
        }

        // Set the state
        this.buttonState = state
    }

    override fun onGameInstChanged(device: BluetoothDevice, state: GameInstData) {
        this.gameInstState = state
    }
}