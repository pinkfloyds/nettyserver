package com.mko.nettyserver.protocol

import com.mko.nettyserver.protocol.Dictionaries.PACKTE_TYPE_DOWNLOAD
import com.mko.nettyserver.protocol.Dictionaries.PACKTE_TYPE_HEARTBEAT
import com.mko.nettyserver.protocol.Dictionaries.PACKTE_TYPE_LOGIN
import com.mko.nettyserver.protocol.Dictionaries.PACKTE_TYPE_RETURN
import com.mko.nettyserver.protocol.Dictionaries.PACKTE_TYPE_TIME
import com.mko.nettyserver.protocol.Dictionaries.PACKTE_TYPE_UPLOAD
import com.mko.nettyserver.protocol.request.HeartBeatRequestPacket
import com.mko.nettyserver.protocol.request.LoginRequestPacket
import com.mko.nettyserver.protocol.request.UploadRequestPacket
import com.mko.nettyserver.protocol.response.DownLoadResponsePacket
import com.mko.nettyserver.protocol.response.ReturnResponsePacket
import com.mko.nettyserver.protocol.response.TimeResponsePacket
import com.mko.nettyserver.util.Unsigned.toUnsigned
import io.netty.buffer.ByteBuf

object PacketCodeC {
    //所有处理包类型
    private val packetTypeMap = mutableMapOf(
            PACKTE_TYPE_LOGIN to LoginRequestPacket::class.java,
            PACKTE_TYPE_HEARTBEAT to HeartBeatRequestPacket::class.java,
            PACKTE_TYPE_UPLOAD to UploadRequestPacket::class.java,
            PACKTE_TYPE_DOWNLOAD to DownLoadResponsePacket::class.java,
            PACKTE_TYPE_RETURN to ReturnResponsePacket::class.java,
            PACKTE_TYPE_TIME to TimeResponsePacket::class.java
    )

    // get/set 不会改变读写指针，而 read/write 会改变读写指针
    //编码
    fun encode(byteBuf: ByteBuf, packet: Packet) {
        // 实际编码过程
        byteBuf.apply {
            writeBytes(byteArrayOf(packet.header))
            writeByte((packet.context.size + 5).toUnsigned())
            writeByte(packet.type.toUnsigned())
            writeByte(packet.index.toUnsigned())
            writeBytes(packet.context)
            writeByte(packet.checkCode.toUnsigned())
        }
    }

    //解码
    fun decode(byteBuf: ByteBuf): Packet? {
        try {
            // 包头+包长度+包类型+包序号+有效数据+校验码 1+1+1+1+n+1
            // 固定包头
            val header = byteBuf.readUnsignedByte()
            // 包长度
            val length = byteBuf.readUnsignedByte()
            // 包类型
            val type = byteBuf.readByte()
            // 包序号
            val index = byteBuf.readUnsignedByte()
            // 内容
            val context = ByteArray(length - 5)
            byteBuf.readBytes(context)
            val checkCode = byteBuf.readUnsignedByte()
            val requestType = packetTypeMap[type] ?: return null
            return requestType.getDeclaredConstructor().newInstance().apply {
                this.context = context
                this.index = index.toInt()
                this.length = length.toInt()
                this.checkCode = checkCode.toInt()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
