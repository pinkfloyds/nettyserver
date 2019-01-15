package com.mko.nettyserver.handler

import io.netty.channel.ChannelHandlerContext
import io.netty.handler.timeout.IdleStateEvent
import io.netty.handler.timeout.IdleStateHandler
import java.util.concurrent.TimeUnit

class IMIdleStateHandler : IdleStateHandler(15L, 0, 0, TimeUnit.SECONDS) {
    /*读空闲时间,在这段时间内如果没有数据读到，就表示连接假死
    写空闲时间，在这段时间如果没有写数据，就表示连接假死
    读写空闲时间，表示在这段时间内如果没有产生数据读或者写，就表示连接假死。写空闲和读写空闲为0，表示我们不关心者两类条件
    单位
    */
    override fun channelIdle(ctx: ChannelHandlerContext, evt: IdleStateEvent) {
        println("15秒内未读到数据，关闭连接")
        ctx.channel().close()
    }
}
