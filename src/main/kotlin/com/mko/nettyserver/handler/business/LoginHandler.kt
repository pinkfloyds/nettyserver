package com.mko.nettyserver.handler.business

import com.mko.nettyserver.protocol.Session
import com.mko.nettyserver.protocol.request.LoginRequestPacket
import com.mko.nettyserver.protocol.response.RepeatResponsePacket
import com.mko.nettyserver.protocol.response.SuccessResponsePacket
import com.mko.nettyserver.protocol.response.TimeResponsePacket
import com.mko.nettyserver.util.SessionUtil.bindSession
import com.mko.nettyserver.util.SessionUtil.hasLogin
import com.mko.nettyserver.util.Unsigned.getGB2312
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ChannelHandler.Sharable
object LoginHandler : SimpleChannelInboundHandler<LoginRequestPacket>(), MysqlHandler {
    override fun channelRead0(ctx: ChannelHandlerContext, msg: LoginRequestPacket) {
        if (hasLogin(ctx.channel())) {
            // 已登录
            ctx.channel().writeAndFlush(RepeatResponsePacket(msg.index))
        } else {
            // 未登录
            val gB2312 = getGB2312(msg.context)
            val session = Session(gB2312.substring(0, 16), gB2312.substring(16, gB2312.length))
            bindSession(session, ctx.channel())
            GlobalScope.launch {
                delay(500)
                ctx.channel().writeAndFlush(TimeResponsePacket(msg.index))
            }
            ctx.channel().writeAndFlush(SuccessResponsePacket(msg.index))
        }

    }
}