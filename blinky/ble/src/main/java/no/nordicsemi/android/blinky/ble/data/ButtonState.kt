package no.nordicsemi.android.blinky.ble.data

import android.bluetooth.BluetoothDevice

class ButtonState: ButtonCallback() {
    var state: String = "none"

    override fun onButtonStateChanged(device: BluetoothDevice, state: String) {
        this.state = state
    }
}