package com.mko.nettyserver.protocol.response

import com.mko.nettyserver.protocol.Dictionaries.PACKTE_RETURN
import com.mko.nettyserver.protocol.Packet

open class ReturnResponsePacket(override val type: Byte = PACKTE_RETURN) : Packet()