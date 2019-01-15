package com.mko.nettyserver.protocol

import java.time.format.DateTimeFormatter

object Dictionaries {
    const val PACKET_HEADER = 0xAA.toByte()

    const val PACKTE_TYPE_LOGIN=0x00.toByte()
    const val PACKTE_TYPE_HEARTBEAT=0x01.toByte()
    const val PACKTE_TYPE_UPLOAD=0x02.toByte()
    const val PACKTE_TYPE_DOWNLOAD=0x03.toByte()
    const val PACKTE_TYPE_RETURN=0x04.toByte()
    const val PACKTE_TYPE_TIME=0x05.toByte()

    const val RETURN_CODE_UNLOGIN=0x00.toByte()
    const val RETURN_CODE_REPEAT=0x01.toByte()
    const val RETURN_CODE_ERROR=0x03.toByte()
    const val RETURN_CODE_LENGTHOUT=0x04.toByte()
    const val RETURN_CODE_SUCCESS=0x05.toByte()

    val formatter=DateTimeFormatter.ofPattern("yyyyMMddhhmmss")

}