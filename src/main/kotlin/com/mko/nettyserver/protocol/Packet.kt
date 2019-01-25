package com.mko.nettyserver.protocol

import com.mko.nettyserver.protocol.Dictionaries.PACKET_HEADER
import java.nio.charset.Charset
import kotlin.experimental.and

abstract class Packet {
    var header = PACKET_HEADER

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

    fun Byte.toUnsigned() = this.and(0x0FF.toByte())

    fun ByteArray.getGB2312() = this.toString(Charset.forName("GB2312"))

    fun String.toGB2312() = this.toByteArray(Charset.forName("GB2312"))

    fun check(temp: Int): Packet? {
        return if (temp == bulidCheckCode()) {
            this
        } else {
            null
        }
    }

    fun bulidCheckCode(): Int {
        try {
            var total = 0
            context.forEach { total += it.toUnsigned() }
            val to = header.toUByte().toInt() + length + index + type
            val string = "00000000${(to + total).toString(2)}"
            checkCode = Integer.valueOf(string.substring(string.length - 8, string.length), 2)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return checkCode
    }

    fun bulidSize() {
        length = context.size + 5
    }

    fun print() {
        println("${header.toUByte().toInt()} ${length} ${index} $type ${context.asList()} $checkCode")
    }
}
