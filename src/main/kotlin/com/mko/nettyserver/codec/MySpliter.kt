package com.mko.nettyserver.codec

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import sun.security.util.Length
import java.nio.charset.Charset

class MySpliter : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, bufferIn: ByteBuf, out: MutableList<Any>) {
        val beginIndex = bufferIn.readerIndex()
        bufferIn.readerIndex(beginIndex)
        if (bufferIn.readableBytes() < 7) {
            return
        }

        val header = bufferIn.getByte(0)
        val packageLength = bufferIn.getUnsignedByte(1).toInt()
        if (bufferIn.readableBytes() < packageLength ) { // 不完整消息
            return
        }
        bufferIn.readerIndex(packageLength + beginIndex)

        val cache = bufferIn.slice(beginIndex,packageLength)
        cache.retain()
        out.add(cache)
    }
}