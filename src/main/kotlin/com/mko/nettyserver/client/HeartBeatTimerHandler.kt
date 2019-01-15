package com.mko.nettyserver.client

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import java.util.concurrent.TimeUnit
import com.mko.nettyserver.protocol.request.HeartBeatRequestPacket
import java.nio.charset.Charset

/**
 *
 */
object HeartBeatTimerHandler : ChannelInboundHandlerAdapter() {
    @Throws(Exception::class)
    override fun channelActive(ctx: ChannelHandlerContext) {
//        for (x in 1..10) {
//            ctx.channel().writeAndFlush(HeartBeatRequestPacket().apply {
//                println("发送心跳包")
//                context = "我进来啦".toByteArray(Charset.forName("gb2312")) //12长
//            })
//        }
        scheduleSendHeartBeat(ctx)
        super.channelActive(ctx)
    }

    private fun scheduleSendHeartBeat(ctx: ChannelHandlerContext) {
        ctx.executor().schedule({
            if (ctx.channel().isActive) {
                println("发送心跳包")
                ctx.channel().writeAndFlush(HeartBeatRequestPacket().apply {
                    context = "我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我进来啦我".toByteArray(Charset.forName("gb2312")) //12长
                })
                scheduleSendHeartBeat(ctx)
            }
        }, 5L, TimeUnit.SECONDS)
    }
}