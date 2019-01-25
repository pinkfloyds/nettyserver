package com.mko.nettyserver.protocol

import java.time.format.DateTimeFormatter

object Dictionaries {
    const val PACKET_HEADER = 0xAA.toByte()

    const val PACKTE_LOGIN = 0x00.toByte()
    const val PACKTE_HEARTBEAT = 0x01.toByte()
    const val PACKTE_UPLOAD = 0x02.toByte()
    const val PACKTE_DOWNLOAD = 0x03.toByte()
    const val PACKTE_RETURN = 0x04.toByte()
    const val PACKTE_TIME = 0x05.toByte()

    const val RETURN_UNLOGIN = 0x00.toByte()
    const val RETURN_REPEAT = 0x01.toByte()
    const val RETURN_ERROR = 0x03.toByte()
    const val RETURN_OUTOFRANGE = 0x04.toByte()
    const val RETURN_SUCCESS = 0x05.toByte()

    val formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss")!!
}