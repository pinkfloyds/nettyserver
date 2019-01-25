package com.mko.nettyserver.client

import com.mko.nettyserver.protocol.response.ReturnResponsePacket
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import java.nio.charset.Charset

object ReturnResponseHandler : SimpleChannelInboundHandler<ReturnResponsePacket>() {
    override fun channelRead0(ctx: ChannelHandlerContext, returnResponsePacket: ReturnResponsePacket) {
        println("收到响应心跳包:${returnResponsePacket.context.toString(Charset.forName("GB2312"))}")
    }
}
