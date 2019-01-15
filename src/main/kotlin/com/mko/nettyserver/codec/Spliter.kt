package com.mko.nettyserver.codec

import com.mko.nettyserver.protocol.Dictionaries.PACKET_HEADER
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.LengthFieldBasedFrameDecoder

class Spliter : LengthFieldBasedFrameDecoder(1024, 1, 1, 4, 0) {
    //长度域拆包器 包最大长 长度域偏移量 长度域长度
    override fun decode(ctx: ChannelHandlerContext, byteBuf: ByteBuf): Any? {
        println("readerindex:${byteBuf.readerIndex()}")
        println("writerIndex:${byteBuf.writerIndex()}")
        if (byteBuf.getByte(byteBuf.readerIndex()) != PACKET_HEADER) {
            println("屏蔽非本协议的客户端")
            ctx.channel().close()
            return null
        }
        return super.decode(ctx, byteBuf)
    }
}
