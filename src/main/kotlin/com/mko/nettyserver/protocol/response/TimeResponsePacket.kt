package com.mko.nettyserver.protocol.response

import com.mko.nettyserver.protocol.Dictionaries.PACKTE_TYPE_TIME
import com.mko.nettyserver.protocol.Dictionaries.formatter
import com.mko.nettyserver.protocol.Packet
import com.mko.nettyserver.util.Unsigned.toGB2312
import java.nio.charset.Charset
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TimeResponsePacket(override val type: Byte = PACKTE_TYPE_TIME) : Packet(){
    override var context: ByteArray =LocalDateTime.now().format(formatter).toGB2312()
    // 14长
}