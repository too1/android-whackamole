package no.nordicsemi.android.blinky.ble.data

import android.bluetooth.BluetoothDevice
import no.nordicsemi.android.ble.callback.profile.ProfileReadResponse
import no.nordicsemi.android.ble.data.Data
import no.nordicsemi.android.blinky.spec.GameData

abstract class ButtonCallback: ProfileReadResponse() {

    override fun onDataReceived(device: BluetoothDevice, data: Data) {
        val btString = data.getStringValue(0).toString()
        val gameDataState = GameData(btString, 123)
        onButtonStateChanged(device, gameDataState)
    }

    abstract fun onButtonStateChanged(device: BluetoothDevice, state: GameData)
    abstract fun onGameStateChanged(device: BluetoothDevice, state: String)
}