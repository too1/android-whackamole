package no.nordicsemi.android.blinky.ble.data

import no.nordicsemi.android.ble.data.Data

class LedData private constructor() {

    companion object {
        fun from(value: String): Data {
            return Data.from(value)
        }
    }

}