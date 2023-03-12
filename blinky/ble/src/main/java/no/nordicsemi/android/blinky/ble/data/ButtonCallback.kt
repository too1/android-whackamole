package no.nordicsemi.android.blinky.ble.data

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic.FORMAT_UINT16
import no.nordicsemi.android.ble.callback.profile.ProfileReadResponse
import no.nordicsemi.android.ble.data.Data
import no.nordicsemi.android.blinky.spec.GameChallenge
import no.nordicsemi.android.blinky.spec.GameData
import no.nordicsemi.android.blinky.spec.GameInstData

abstract class ButtonCallback: ProfileReadResponse() {

    override fun onDataReceived(device: BluetoothDevice, data: Data) {
        val gameData: GameData = GameData("", 0)
        if(data.getByte(0)?.toInt()?.toChar() == 'A') {
            // Challenge start
            gameData.msg = "New challenge"
            val targetTime = (data.getByte(2)!!.toInt() * 256) + data.getByte(3)!!.toInt()
            gameData.targetTime = targetTime
        } else if(data.getByte(0)?.toInt()?.toChar() == 'B') {
            // Challenge finish
            val finalTime = data.getIntValue(Data.FORMAT_UINT16_BE, 2)!!
            val time = data.getIntValue(Data.FORMAT_UINT16_BE, 4)!!
            val success = (data.getByte(6)!!.toInt() == 1)
            val points = data.getIntValue(Data.FORMAT_UINT16_BE, 7)!!
            val fouls = data.getIntValue(Data.FORMAT_UINT16_BE, 9)!!
            gameData.msg = if(success) "Success!!" else "Failure!"
            gameData.targetTime = finalTime
            gameData.pointIncrement = points
            gameData.foulIncrement = fouls
            gameData.totalScore++
            gameData.challenges.add(GameChallenge(finalTime,  time, success, fouls))
        } else if(data.getByte(0)?.toInt()?.toChar() == 'C') {
            // Round start
            gameData.msg = "Round start"
            gameData.roundNumber = data.getByte(1)!!.toInt() + 1
        } else if(data.getByte(0)?.toInt()?.toChar() == 'D') {
            // Game start
            gameData.msg = "Game start"
            gameData.startNewGame = true
        } else if(data.getByte(0)?.toInt()?.toChar() == 'E') {
            // Game finish
            gameData.msg = "Game finish"
        }

        //val gameDataState = GameData(btString, score)
        onButtonStateChanged(device, gameData)
    }

    abstract fun onButtonStateChanged(device: BluetoothDevice, state: GameData)

    abstract fun onGameInstChanged(device: BluetoothDevice, state: GameInstData)
}