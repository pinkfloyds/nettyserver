package com.mko.nettyserver.protocol.response

import com.mko.nettyserver.protocol.Dictionaries

data class RepeatResponsePacket(override var index: Int) : ReturnResponsePacket() {
    override var context: ByteArray = byteArrayOf(Dictionaries.RETURN_REPEAT, 0x00)
}