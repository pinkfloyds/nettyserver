package com.mko.nettyserver.protocol.response

import com.mko.nettyserver.protocol.Dictionaries.PACKTE_TYPE_DOWNLOAD
import com.mko.nettyserver.protocol.Packet

data class DownLoadResponsePacket(override val type: Byte = PACKTE_TYPE_DOWNLOAD) : Packet()