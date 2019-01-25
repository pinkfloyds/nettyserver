package com.mko.nettyserver.handler

import com.mko.nettyserver.protocol.request.HeartBeatRequestPacket
import com.mko.nettyserver.protocol.response.SuccessResponsePacket
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

@ChannelHandler.Sharable
object HeartBeatRequestHandler : SimpleChannelInboundHandler<HeartBeatRequestPacket>() {
    override fun channelRead0(ctx: ChannelHandlerContext, msg: HeartBeatRequestPacket) {
        println("收到心跳包")
        ctx.channel().writeAndFlush(SuccessResponsePacket(msg.index))
    }
}
