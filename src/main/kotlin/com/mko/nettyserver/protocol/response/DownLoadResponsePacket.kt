package com.mko.nettyserver.protocol.response

import com.mko.nettyserver.protocol.Dictionaries.PACKTE_DOWNLOAD
import com.mko.nettyserver.protocol.Packet

data class DownLoadResponsePacket(val command: Short, val data: String) : Packet() {
    override var context = byteArrayOf(command.toByte()).plus(data.toGB2312())
    override val type: Byte = PACKTE_DOWNLOAD
}