package com.mko.nettyserver.handler

import com.mko.nettyserver.util.SessionUtil
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

class AuthHandler : ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close()
        } else {
            // 一行代码实现逻辑的删除
            ctx.pipeline().remove(this)
            super.channelRead(ctx, msg)
        }
    }

    override fun handlerRemoved(ctx: ChannelHandlerContext) {
        if (SessionUtil.hasLogin(ctx.channel())) {
            println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除")
        } else {
            println("无登录验证，强制关闭连接!")
        }
    }
}