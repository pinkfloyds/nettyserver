package com.mko.nettyserver.handler.business

import com.mko.nettyserver.protocol.Packet
import com.mko.nettyserver.protocol.request.LoginRequestPacket
import com.mko.nettyserver.protocol.request.UploadRequestPacket
import io.netty.channel.ChannelHandler
import io.netty.channel.SimpleChannelInboundHandler

import io.netty.channel.ChannelHandlerContext
import java.util.HashMap

@ChannelHandler.Sharable
class RouteHandler : SimpleChannelInboundHandler<UploadRequestPacket>() {
//        handlerMap = mutableMapOf(
//                "login" to LoginHandler::class.java
//        )
//

    @Throws(Exception::class)
    override fun channelRead0(ctx: ChannelHandlerContext, uploadRequestPacket: UploadRequestPacket) {
//        handlerMap[uploadRequestPacket.type].channelRead(ctx, packet)
    }
}
