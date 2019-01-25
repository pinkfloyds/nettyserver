package com.mko.nettyserver.protocol.response

import com.mko.nettyserver.protocol.Dictionaries.RETURN_ERROR

data class ErrorResponsePacket(override var index: Int) : ReturnResponsePacket() {
    override var context: ByteArray = byteArrayOf(RETURN_ERROR, 0x00)
}