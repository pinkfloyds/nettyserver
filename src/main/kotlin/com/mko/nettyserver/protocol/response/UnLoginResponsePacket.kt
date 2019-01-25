package com.mko.nettyserver.protocol.response

import com.mko.nettyserver.protocol.Dictionaries.RETURN_UNLOGIN

data class UnLoginResponsePacket(override var index: Int) : ReturnResponsePacket() {
    override var context: ByteArray = byteArrayOf(RETURN_UNLOGIN, 0x00)
}