package com.mko.nettyserver.handler

import com.mko.nettyserver.protocol.Dictionaries.RETURN_CODE_SUCCESS
import com.mko.nettyserver.protocol.request.HeartBeatRequestPacket
import com.mko.nettyserver.protocol.response.ReturnResponsePacket
import com.mko.nettyserver.util.Unsigned.getGB2312
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import java.nio.charset.Charset


@ChannelHandler.Sharable
object HeartBeatRequestHandler : SimpleChannelInboundHandler<HeartBeatRequestPacket>() {
    override fun channelRead0(ctx: ChannelHandlerContext, requestPacket: HeartBeatRequestPacket) {
        println("收到:$requestPacket ${Thread.currentThread().id}")
//        ctx.executor().newSucceededFuture()
        ctx.channel().writeAndFlush(ReturnResponsePacket().apply {
            context=requestPacket.context
        })
    }

    fun ByteArray.toGB2312() = String(this, Charset.forName("GB2312"))
}
