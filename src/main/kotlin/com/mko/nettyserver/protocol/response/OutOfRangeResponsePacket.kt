package com.mko.nettyserver.protocol.response

import com.mko.nettyserver.protocol.Dictionaries.RETURN_OUTOFRANGE

data class OutOfRangeResponsePacket(override var index: Int) : ReturnResponsePacket() {
    override var context: ByteArray = byteArrayOf(RETURN_OUTOFRANGE, 0x00)
}