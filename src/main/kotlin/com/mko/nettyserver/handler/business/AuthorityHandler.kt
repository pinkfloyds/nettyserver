package com.mko.nettyserver.handler.business

import com.mko.nettyserver.protocol.request.UploadRequestPacket
import com.mko.nettyserver.protocol.response.SuccessResponsePacket
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

/**
 * 报告授权状态
 */
@ChannelHandler.Sharable
object AuthorityHandler : SimpleChannelInboundHandler<UploadRequestPacket>() {
    override fun channelRead0(ctx: ChannelHandlerContext, msg: UploadRequestPacket) {
        println("command:${msg.command.first().toUByte()}")
        msg.data.forEach { println(it) }
        ctx.channel().writeAndFlush(SuccessResponsePacket(msg.index))
//        GlobalScope.launch {
//            println(msg)
//        }
    }
}