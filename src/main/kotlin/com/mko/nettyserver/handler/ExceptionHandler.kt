package com.mko.nettyserver.handler

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

private class ExceptionHandler : ChannelInboundHandlerAdapter() {
    private val timeoutSeconds: Int = 0

    @Throws(Exception::class)
    override fun channelRegistered(ctx: ChannelHandlerContext) {
        ctx.executor().schedule<ChannelHandlerContext>(
                { ctx.fireExceptionCaught(TimeoutException()) }, timeoutSeconds.toLong(), TimeUnit.SECONDS)
                .addListener { }
                .addListener { }
        super.channelRegistered(ctx)
    }
}