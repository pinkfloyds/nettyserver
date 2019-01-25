package com.mko.nettyserver.protocol.request

import com.mko.nettyserver.protocol.Dictionaries.PACKTE_UPLOAD
import com.mko.nettyserver.protocol.Packet

data class UploadRequestPacket(override val type: Byte = PACKTE_UPLOAD) : Packet() {
    val command = ByteArray(1)
    var data: ByteArray = byteArrayOf()

    fun bulid() {
        data = ByteArray(context.size)
        System.arraycopy(context, 0, command, 0, 1)
        System.arraycopy(context, 0, data, 0, context.size - 1)
    }
}