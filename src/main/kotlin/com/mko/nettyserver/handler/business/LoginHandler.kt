package com.mko.nettyserver.handler.business

import com.mko.nettyserver.ExecutorServiceLoop
import com.mko.nettyserver.protocol.request.LoginRequestPacket
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

class LoginHandler : SimpleChannelInboundHandler<LoginRequestPacket>() {

    override fun channelRead0(ctx: ChannelHandlerContext, msg: LoginRequestPacket) {

        ExecutorServiceLoop.put(Runnable {

        })

    }


}