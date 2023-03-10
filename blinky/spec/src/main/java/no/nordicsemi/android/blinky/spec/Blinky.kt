package no.nordicsemi.android.blinky.spec

import kotlinx.coroutines.flow.StateFlow

data class GameData (var msg: String, var score: Int)

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

    val gameState: StateFlow<String>

    /**
     * Controls the LED state.
     *
     * @param state the new state of the LED.
     */
    suspend fun turnLed(state: String)
}