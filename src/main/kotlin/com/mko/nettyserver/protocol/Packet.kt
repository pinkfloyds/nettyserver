package com.mko.nettyserver.protocol

import com.alibaba.fastjson.annotation.JSONField
import com.mko.nettyserver.protocol.Dictionaries.PACKET_HEADER

abstract class Packet {
    val header = PACKET_HEADER

    open var length: Int = 0

    abstract val type: Byte

    open var index: Int = 0

    open var context: ByteArray = byteArrayOf()

    open var checkCode: Int = 0

    /*fun getCheckCode(packet: Packet){
        packet.run {
            val total = Integer.toHexString(context + header + index + type)
            packet.checkCode=total.substring(total.length-8,total.length).toByte()
        }
    }*/
}
