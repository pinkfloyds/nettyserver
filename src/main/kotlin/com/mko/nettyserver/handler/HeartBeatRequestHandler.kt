package com.mko.nettyserver.handler

import com.mko.nettyserver.protocol.request.HeartBeatRequestPacket
import com.mko.nettyserver.protocol.response.ReturnResponsePacket
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import java.nio.charset.Charset


@ChannelHandler.Sharable
object HeartBeatRequestHandler : SimpleChannelInboundHandler<HeartBeatRequestPacket>() {
    override fun channelRead0(ctx: ChannelHandlerContext, requestPacket: HeartBeatRequestPacket) {
        ctx.channel().writeAndFlush(ReturnResponsePacket().apply {
            context=requestPacket.context
        })
    }

    fun ByteArray.toGB2312() = String(this, Charset.forName("GB2312"))
}
