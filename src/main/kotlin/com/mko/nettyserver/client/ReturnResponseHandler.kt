package com.mko.nettyserver.client

import com.mko.nettyserver.protocol.Dictionaries.RETURN_CODE_SUCCESS
import com.mko.nettyserver.protocol.request.HeartBeatRequestPacket
import com.mko.nettyserver.protocol.response.ReturnResponsePacket
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import java.nio.charset.Charset
import java.util.*

object ReturnResponseHandler : SimpleChannelInboundHandler<ReturnResponsePacket>() {
    override fun channelRead0(ctx: ChannelHandlerContext, returnResponsePacket: ReturnResponsePacket) {
        println("收到响应心跳包:${returnResponsePacket.context.toString(Charset.forName("GB2312"))}")
    }
}
