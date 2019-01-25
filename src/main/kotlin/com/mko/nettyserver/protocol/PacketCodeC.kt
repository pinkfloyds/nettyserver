package com.mko.nettyserver.protocol

import com.mko.nettyserver.protocol.Dictionaries.PACKTE_DOWNLOAD
import com.mko.nettyserver.protocol.Dictionaries.PACKTE_HEARTBEAT
import com.mko.nettyserver.protocol.Dictionaries.PACKTE_LOGIN
import com.mko.nettyserver.protocol.Dictionaries.PACKTE_TIME
import com.mko.nettyserver.protocol.Dictionaries.PACKTE_UPLOAD
import com.mko.nettyserver.protocol.request.HeartBeatRequestPacket
import com.mko.nettyserver.protocol.request.LoginRequestPacket
import com.mko.nettyserver.protocol.request.UploadRequestPacket
import com.mko.nettyserver.protocol.response.DownLoadResponsePacket
import com.mko.nettyserver.protocol.response.TimeResponsePacket
import com.mko.nettyserver.util.Unsigned.toUnsigned
import io.netty.buffer.ByteBuf

object PacketCodeC {
    // 所有处理包类型
    private val packetTypeMap = mutableMapOf(
            PACKTE_LOGIN to LoginRequestPacket::class.java,
            PACKTE_HEARTBEAT to HeartBeatRequestPacket::class.java,
            PACKTE_UPLOAD to UploadRequestPacket::class.java,
            PACKTE_DOWNLOAD to DownLoadResponsePacket::class.java,
            PACKTE_TIME to TimeResponsePacket::class.java
    )

    // get/set 不会改变读写指针，而 read/write 会改变读写指针
    // 编码
    fun encode(byteBuf: ByteBuf, packet: Packet) {
        packet.bulidSize()
        packet.bulidCheckCode()
        // 实际编码过程
        byteBuf.apply {
            writeBytes(byteArrayOf(packet.header))
            writeByte(packet.length)
            writeByte(packet.index)
            writeByte(packet.type.toUnsigned())
            writeBytes(packet.context)
            writeByte(packet.checkCode)
        }
        println("编码")
        packet.print()
    }

    // 解码
    fun decode(byteBuf: ByteBuf): Packet? {
        try {
            // 包头+包长度+包序号+包类型+有效数据+校验码 1+1+1+1+n+1
            // 固定包头
            val header = byteBuf.readUnsignedByte()
            // 包长度
            val length = byteBuf.readUnsignedByte()
            // 包序号
            val index = byteBuf.readUnsignedByte()
            // 包类型
            val type = byteBuf.readByte()
            // 内容
            val context = ByteArray(length - 5)
            byteBuf.readBytes(context)
            val checkCode = byteBuf.readUnsignedByte()
            val requestType = packetTypeMap[type] ?: return null
            val apply = requestType.getDeclaredConstructor().newInstance().apply {
                this.context = context
                this.index = index.toInt()
                this.header = header.toByte()
                this.length = length.toInt()
                this.checkCode = checkCode.toInt()
            }
            if (apply is UploadRequestPacket) {
                apply.bulid()
            }
            apply.check(checkCode.toInt())
            println("解码")
            apply.print()
            return apply
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private fun ByteArray.toHex() {
        this.forEach { print(it.toInt().toString(16) + " ") }
        println()
    }
}
