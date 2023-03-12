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
        actualGameState.currentGameHighscore.score = actualGameState.totalScore
        // Update current round from persistent variables
        if (state.roundNumber > 0) {
            currentRound = state.roundNumber
        }
        else {
            state.roundNumber = currentRound
        }
        if(state.startNewGame) {
            // In case of a new game, re-initialize the gamestate
            actualGameState = GameData("", 0)
        }
        if(state.gameOver) {
            actualGameState.highscores.add(actualGameState.currentGameHighscore);
        }
        // Update challenge list from persistent variables
        if (state.challenges.isNotEmpty()) {
            while (state.challenges.isNotEmpty()) {
                var newChallenge = state.challenges.removeFirst()
                newChallenge.index = actualGameState.numChallenges
                actualGameState.numChallenges++
                actualGameState.challenges.add(newChallenge)
            }
        }
        if(actualGameState.challenges.isNotEmpty()) {
            state.challenges = actualGameState.challenges.takeLast(200) as MutableList<GameChallenge>
            state.challenges.reverse()
            state.numChallenges = actualGameState.numChallenges
        }
        // Update active list of highscores
        if(state.currentGameHighscore.name != "") actualGameState.currentGameHighscore.name = state.currentGameHighscore.name
        state.highscores.clear()
        state.highscores.addAll(actualGameState.highscores)
        state.highscores.add(actualGameState.currentGameHighscore)
        state.highscores.sort()
        state.highscores.reverse()
        // Update target time from persistent variable
        if(state.targetTime != 0) {
            actualGameState.targetTime = state.targetTime
        }
        state.targetTime = actualGameState.targetTime

        // Update number of controllers
        if(state.numControllers > 0) {
            actualGameState.numControllers = state.numControllers
        }
        else state.numControllers = actualGameState.numControllers

        // Set the state
        this.buttonState = state
    }

    override fun onGameInstChanged(device: BluetoothDevice, state: GameInstData) {
        this.gameInstState = state
    }
}