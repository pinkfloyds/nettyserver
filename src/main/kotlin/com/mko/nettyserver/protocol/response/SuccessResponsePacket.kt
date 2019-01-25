package com.mko.nettyserver.protocol.response

import com.mko.nettyserver.protocol.Dictionaries.RETURN_SUCCESS

data class SuccessResponsePacket(override var index: Int) : ReturnResponsePacket() {
    override var context: ByteArray = byteArrayOf(RETURN_SUCCESS, 0x00)
}