package com.mko.nettyserver.codec

import com.mko.nettyserver.protocol.Packet
import com.mko.nettyserver.protocol.PacketCodeC
import com.mko.nettyserver.protocol.response.ErrorResponsePacket
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageCodec

@ChannelHandler.Sharable
object PacketCodecHandler : MessageToMessageCodec<ByteBuf, Packet>() {
    override fun encode(ctx: ChannelHandlerContext, packet: Packet, out: MutableList<Any>) {
        val byteBuf = ctx.channel().alloc().ioBuffer()
        PacketCodeC.encode(byteBuf, packet)
        out.add(byteBuf)
    }

    override fun decode(ctx: ChannelHandlerContext, byteBuf: ByteBuf, out: MutableList<Any?>) {
        out.add(PacketCodeC.decode(byteBuf)
                ?: ctx.channel().writeAndFlush(ErrorResponsePacket(byteBuf.getUnsignedByte(3).toInt())))
    }
}
