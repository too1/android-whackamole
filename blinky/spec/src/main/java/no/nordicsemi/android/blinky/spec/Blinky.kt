package no.nordicsemi.android.blinky.spec

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

data class GameChallenge (var time: Int, var target: Int, var success: Boolean, var fouls: Int) {
    var index: Int = 0
}

data class GameHighscoreEntry (var score: Int, var name: String, var minTime: Int): Comparable<GameHighscoreEntry> {
    var email: String = ""

    override fun compareTo(other: GameHighscoreEntry): Int = when {
        this.score != other.score -> this.score compareTo other.score
        this.minTime != other.minTime -> other.minTime compareTo this.minTime
        else -> 0
    }
}

data class GameData (var msg: String, var score: Int) {
    var playerName: String = "John Smith"
    var numControllers: Int = 0
    var targetTime: Int = 0
    var pointIncrement: Int = 0
    var foulIncrement: Int = 0
    var totalPoints: Int = 0
    var totalFouls: Int = 0
    var totalScore: Int = 0
    var roundNumber: Int = 0
    var numChallenges: Int = 0
    var challenges: MutableList<GameChallenge> = mutableListOf()//GameChallenge(500, 1000, true, 0), GameChallenge(1200, 1000, false, 2))
    var startNewGame: Boolean = false
    var gameOver: Boolean = false
    var highscores: MutableList<GameHighscoreEntry> = mutableListOf(GameHighscoreEntry(25, "Bob kåre", 254), GameHighscoreEntry(23, "Kakk barnes", 312), GameHighscoreEntry(30, "Olly bardun", 298), GameHighscoreEntry(30, "Pekk Perry", 123))
    var currentGameHighscore: GameHighscoreEntry = GameHighscoreEntry(0, "", 0)
}

data class GameStatus (var totalScore: Int){
    fun increment() {
        totalScore++
    }
}

data class GameInstData (var msg: String, var scoreInc: Int) {
    var score: Int = 0
}

interface Blinky {

    enum class State {
        LOADING,
        READY,
        NOT_AVAILABLE
    }

    /**
     * Connects to the device.
     */
    suspend fun connect()

    /**
     * Disconnects from the device.
     */
    fun release()

    /**
     * The current state of the blinky.
     */
    val state: StateFlow<State>

    /**
     * The current state of the LED.
     */
    val ledState: StateFlow<String>

    /**
     * The current state of the button.
     */
    val buttonState: StateFlow<GameData>

    val gameInstState: SharedFlow<GameInstData>

    var gameStatus: GameStatus

    val gameState: StateFlow<String>

    /**
     * Controls the LED state.
     *
     * @param state the new state of the LED.
     */
    suspend fun turnLed(state: String)
}