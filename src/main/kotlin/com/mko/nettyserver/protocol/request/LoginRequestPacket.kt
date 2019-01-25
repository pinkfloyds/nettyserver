package com.mko.nettyserver.protocol.request

import com.mko.nettyserver.protocol.Dictionaries.PACKTE_LOGIN
import com.mko.nettyserver.protocol.Packet

data class LoginRequestPacket(override val type: Byte = PACKTE_LOGIN) : Packet()