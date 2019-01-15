package com.mko.nettyserver.protocol.request

import com.mko.nettyserver.protocol.Dictionaries.PACKTE_TYPE_UPLOAD
import com.mko.nettyserver.protocol.Packet

data class UploadRequestPacket(override val type: Byte = PACKTE_TYPE_UPLOAD) : Packet()