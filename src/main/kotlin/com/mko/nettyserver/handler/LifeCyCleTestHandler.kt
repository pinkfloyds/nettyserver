package com.mko.nettyserver.handler

import com.mko.nettyserver.util.SessionUtil.unBindSession
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter

class LifeCyCleTestHandler : ChannelInboundHandlerAdapter() {
    @Throws(Exception::class)
    override fun handlerAdded(ctx: ChannelHandlerContext?) {
//        println("逻辑处理器被添加：handlerAdded()")
        super.handlerAdded(ctx)
    }

    @Throws(Exception::class)
    override fun channelRegistered(ctx: ChannelHandlerContext) {
//        println("channel 绑定到线程(NioEventLoop)：channelRegistered()")
        super.channelRegistered(ctx)
    }

    @Throws(Exception::class)
    override fun channelActive(ctx: ChannelHandlerContext) {
//        println("channel 准备就绪：channelActive()")
        super.channelActive(ctx)
    }

    @Throws(Exception::class)
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
//        println("channel 有数据可读：channelRead()")
        super.channelRead(ctx, msg)
    }

    @Throws(Exception::class)
    override fun channelReadComplete(ctx: ChannelHandlerContext) {
//        println("channel 某次数据读完：channelReadComplete()")
        super.channelReadComplete(ctx)

    }

    @Throws(Exception::class)
    override fun channelInactive(ctx: ChannelHandlerContext) {
//        println("channel 被关闭：channelInactive()")
        unBindSession(ctx.channel())
        super.channelInactive(ctx)
    }

    @Throws(Exception::class)
    override fun channelUnregistered(ctx: ChannelHandlerContext) {
//        println("channel 取消线程(NioEventLoop) 的绑定: channelUnregistered()")
        super.channelUnregistered(ctx)
    }

    @Throws(Exception::class)
    override fun handlerRemoved(ctx: ChannelHandlerContext?) {
//        println("逻辑处理器被移除：handlerRemoved()")
        super.handlerRemoved(ctx)
    }
}
