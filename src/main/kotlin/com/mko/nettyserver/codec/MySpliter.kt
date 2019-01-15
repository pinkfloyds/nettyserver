package com.mko.nettyserver.codec

import com.mko.nettyserver.protocol.Dictionaries
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class MySpliter : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, bufferIn: ByteBuf, out: MutableList<Any>) {
        val beginIndex = bufferIn.readerIndex()
        if (bufferIn.getByte(0) != Dictionaries.PACKET_HEADER) {
            println("屏蔽非本协议的客户端")
            ctx.channel().close()
            return
        }
        val packageLength = bufferIn.getUnsignedByte(1).toInt()
        if (bufferIn.readableBytes() < packageLength || bufferIn.readableBytes() < 6) { // 不完整消息
            bufferIn.readerIndex(beginIndex)
            return
        }
        bufferIn.readerIndex(packageLength + beginIndex)

        val cache = bufferIn.slice(beginIndex,packageLength)
        cache.retain()
        out.add(cache)
    }
}