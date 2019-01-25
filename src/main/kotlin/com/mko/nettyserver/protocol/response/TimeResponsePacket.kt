package com.mko.nettyserver.protocol.response

import com.mko.nettyserver.protocol.Dictionaries.formatter
import java.time.LocalDateTime

data class TimeResponsePacket(override var index: Int) : ReturnResponsePacket() {
    override var context: ByteArray = LocalDateTime.now().format(formatter).toGB2312()
    // 14长
}