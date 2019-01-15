package com.mko.nettyserver.protocol.response

import com.mko.nettyserver.protocol.Dictionaries.PACKTE_TYPE_RETURN
import com.mko.nettyserver.protocol.Dictionaries.RETURN_CODE_SUCCESS
import com.mko.nettyserver.protocol.Packet

data class ReturnResponsePacket(override val type: Byte = PACKTE_TYPE_RETURN) : Packet()