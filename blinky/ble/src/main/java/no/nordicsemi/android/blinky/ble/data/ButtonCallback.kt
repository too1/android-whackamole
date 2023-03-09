package no.nordicsemi.android.blinky.ble.data

import android.bluetooth.BluetoothDevice
import no.nordicsemi.android.ble.callback.profile.ProfileReadResponse
import no.nordicsemi.android.ble.data.Data

abstract class ButtonCallback: ProfileReadResponse() {

    override fun onDataReceived(device: BluetoothDevice, data: Data) {
        val buttonState = data.getStringValue(0).toString()
        onButtonStateChanged(device, buttonState)
        //val bstate2: String = "Bstate2"
        onGameStateChanged(device, buttonState)
    }

    abstract fun onButtonStateChanged(device: BluetoothDevice, state: String)
    abstract fun onGameStateChanged(device: BluetoothDevice, state: String)
}