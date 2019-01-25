package com.mko.nettyserver.protocol.request

import com.mko.nettyserver.protocol.Dictionaries.PACKTE_HEARTBEAT
import com.mko.nettyserver.protocol.Packet

data class HeartBeatRequestPacket(override val type: Byte = PACKTE_HEARTBEAT) : Packet()